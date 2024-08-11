package valuemakers.app.rentye.controller;

import jakarta.validation.Valid;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import valuemakers.app.rentye.dto.ChangePasswordDTO;
import valuemakers.app.rentye.util.RentYeUserDetails;
import valuemakers.app.rentye.service.RentYeUserDetailsManager;
import valuemakers.app.rentye.dto.UserAccountReducedDTO;

@Controller
public class UserAccountController {
    private final RentYeUserDetailsManager userDetailsManager;
    private final PasswordEncoder passwordEncoder;
    public UserAccountController(RentYeUserDetailsManager userDetailsManager, PasswordEncoder passwordEncoder) {
        this.userDetailsManager = userDetailsManager;
        this.passwordEncoder = passwordEncoder;
    }

    @ModelAttribute("userDetails")
    public RentYeUserDetails getUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
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
    public String changePassword(Model model) {
        model.addAttribute("changePasswordDTO", new ChangePasswordDTO());
        return "changePassword";
    }

    @PostMapping("/changePassword")
    public String changePasswordPost(@ModelAttribute ChangePasswordDTO changePasswordDTO, @ModelAttribute("userDetails") RentYeUserDetails userDetails, RedirectAttributes redirectAttributes) {
        String newPassword = changePasswordDTO.getNewPassword();

        if (!newPassword.equals(changePasswordDTO.getNewPasswordRetyped()))
            redirectAttributes.addAttribute("errorMessage", "Retyped password does not match");
        else if (newPassword.isEmpty())
            redirectAttributes.addAttribute("errorMessage", "Password can't be blank");
        else {
            String encodedPassword = passwordEncoder.encode(newPassword);
            userDetailsManager.changePassword("", encodedPassword);
            Authentication authentication = new PreAuthenticatedAuthenticationToken(userDetails, encodedPassword, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            redirectAttributes.addAttribute("confirmMessage", "Password changed successfully");
        }

        return "redirect:/accountProfile";
    }
}
