package valuemakers.app.rentye.controller;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import valuemakers.app.rentye.RentYeUserDetails;
import valuemakers.app.rentye.RentYeUserDetailsManager;
import valuemakers.app.rentye.repository.UserAccountRepository;

@Controller
public class UserAccountController {
    private RentYeUserDetailsManager userDetailsManager;
    private ModelMapper modelMapper;
    private UserAccountRepository userAccountRepository;
    public UserAccountController(RentYeUserDetailsManager userDetailsManager, ModelMapper modelMapper, UserAccountRepository userAccountRepository) {
        this.userDetailsManager = userDetailsManager;
        this.modelMapper = modelMapper;
        this.userAccountRepository = userAccountRepository;
    }

    @ModelAttribute("userDetails")
    public RentYeUserDetails getUserDetails() {
        Authentication authentication;
        authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken))
            return userDetailsManager.loadUserByUsername(authentication.getName());
        else
            return null;
    }

    @GetMapping("/accountProfile")
    public String accountProfileSave(Model model, @ModelAttribute("userDetails") RentYeUserDetails userDetails) {
        UserAccountDTO userAccountDTO = modelMapper.map(userDetails.getUserAccount(), UserAccountDTO.class);
        model.addAttribute("userAccountDTO", userAccountDTO);
        return "accountProfile";
    }

    @PostMapping("/accountProfile")
    public String accountProfile(@ModelAttribute("userDetails") RentYeUserDetails userDetails, @Valid @ModelAttribute UserAccountDTO userAccountDTO, BindingResult result) {
        userAccountDTO.normalizeStringAttributes();

        if (!userDetails.getUserAccount().getEmail().equals(userAccountDTO.getEmail()) &&
                userAccountRepository.findByEmail(userAccountDTO.getEmail())!=null)
            result.addError(new FieldError("userAccountDTO", "email", "Email already exists"));

        if (result.hasErrors()) {
            return "accountProfile";
        }

        userDetails.getUserAccount().setEmail(userAccountDTO.getEmail());
        userDetails.getUserAccount().setFirstName(userAccountDTO.getFirstName());
        userDetails.getUserAccount().setLastName(userAccountDTO.getLastName());
        userDetailsManager.updateUser(userDetails);

        Authentication authentication = new PreAuthenticatedAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return "redirect:/";
    }

    @GetMapping("/changePassword")
    public String changePassword() {
        return "changePassword";
    }
}
