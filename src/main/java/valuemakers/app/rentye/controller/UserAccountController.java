package valuemakers.app.rentye.controller;

import jakarta.validation.Valid;
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
import valuemakers.app.rentye.UserAccountReducedDTO;

@Controller
public class UserAccountController {
    private RentYeUserDetailsManager userDetailsManager;
    public UserAccountController(RentYeUserDetailsManager userDetailsManager) {
        this.userDetailsManager = userDetailsManager;
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
        UserAccountReducedDTO userAccountReducedDTO = userDetails.getUserAccountDTO();
        model.addAttribute("userAccountReducedDTO", userAccountReducedDTO);
        return "accountProfile";
    }

    @PostMapping("/accountProfile")
    public String accountProfile(@ModelAttribute("userDetails") RentYeUserDetails userDetails, @Valid @ModelAttribute UserAccountReducedDTO userAccountReducedDTO, BindingResult result) {
        userAccountReducedDTO.normalizeStringAttributes();

        if (!userDetails.getUserAccountDTO().getEmail().equals(userAccountReducedDTO.getEmail()) &&
                userDetailsManager.userEmailExists(userAccountReducedDTO.getEmail()))
            result.addError(new FieldError("userAccountReducedDTO", "email", "Email already exists"));

        if (result.hasErrors()) {
            return "accountProfile";
        }

        userDetails.getUserAccountDTO().setEmail(userAccountReducedDTO.getEmail());
        userDetails.getUserAccountDTO().setFirstName(userAccountReducedDTO.getFirstName());
        userDetails.getUserAccountDTO().setLastName(userAccountReducedDTO.getLastName());
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
