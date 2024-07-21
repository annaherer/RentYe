package valuemakers.app.rentye.controller;

import jakarta.validation.Valid;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import valuemakers.app.rentye.RentYeUserDetails;
import valuemakers.app.rentye.RentYeUserDetailsManager;
import valuemakers.app.rentye.dto.UserAccountDTO;

import java.util.Collection;

@Controller
@RequestMapping("/admin")
@Secured("ROLE_ADMIN")
public class AdminController {
    private final RentYeUserDetailsManager userDetailsManager;
    private final PasswordEncoder passwordEncoder;

    public AdminController(RentYeUserDetailsManager userDetailsManager, PasswordEncoder passwordEncoder) {
        this.userDetailsManager = userDetailsManager;
        this.passwordEncoder = passwordEncoder;
    }

    @ModelAttribute("allUsers")
    public Collection<RentYeUserDetails> getAllUsers() {
        return userDetailsManager.loadAllUsers();
    }

    @GetMapping("/adminPanel")
    public String adminPanel() {
        return "adminPanel";
    }

    @GetMapping("/addUser")
    public String addUser(Model model) {
        UserAccountDTO userAccountDTO = new UserAccountDTO();
        model.addAttribute("userAccountDTO", userAccountDTO);
        return "userAccountAddEdit";
    }

    @PostMapping("/addUser")
    public String addUserPost(@Valid @ModelAttribute UserAccountDTO userAccountDTO, BindingResult result) {
        userAccountDTO.normalizeStringAttributes();

        if (userAccountDTO.getNewPassword().isEmpty())
            result.addError(new FieldError("userAccountDTO", "newPassword", "Password cannot be blank"));

        if (userDetailsManager.userExists(userAccountDTO.getUsername()))
            result.addError(new FieldError("userAccountDTO", "username", "Username already exists"));

        if (userDetailsManager.userEmailExists(userAccountDTO.getEmail()))
            result.addError(new FieldError("userAccountDTO", "email", "Email already exists"));

        if (result.hasErrors()) {
            return "userAccountAddEdit";
        }

        userAccountDTO.setPassword(passwordEncoder.encode(userAccountDTO.getNewPassword()));

        userDetailsManager.createUser(new RentYeUserDetails(userAccountDTO));

        return "redirect:./adminPanel";
    }

    @GetMapping("/editUser/{id}")
    public String editUser(@PathVariable Long id, Model model) {
        UserAccountDTO userAccountDTO = userDetailsManager.loadUserById(id).getUserAccountDTO();
        model.addAttribute("userAccountDTO", userAccountDTO);
        return "userAccountAddEdit";
    }

    @PostMapping("/editUser/{id}")
    public String editUserPost(@Valid @ModelAttribute UserAccountDTO userAccountDTO, BindingResult result) {
        userAccountDTO.normalizeStringAttributes();
        if (!userAccountDTO.getNewPassword().isEmpty())
            userAccountDTO.setPassword(passwordEncoder.encode(userAccountDTO.getNewPassword()));

        try {
            if (!userDetailsManager.loadUserByEmail(userAccountDTO.getEmail()).getUsername().equals(userAccountDTO.getUsername()))
                result.addError(new FieldError("userAccountDTO", "email", "Email already exists"));
        } catch (UsernameNotFoundException e) {}

        if (SecurityContextHolder.getContext().getAuthentication().getName().equals(userAccountDTO.getUsername()))
            result.addError(new ObjectError("userAccountDTO", "You can't edit yourself in this panel, go to your account profile"));

        if (result.hasErrors()) {
            return "userAccountAddEdit";
        }

        userDetailsManager.updateUser(new RentYeUserDetails(userAccountDTO));

        return "redirect:../adminPanel";
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        String username = userDetailsManager.loadUserById(id).getUsername();

        if (SecurityContextHolder.getContext().getAuthentication().getName().equals(username))
            redirectAttributes.addAttribute("message", "You cannot delete yourself");
        else
            userDetailsManager.deleteUser(username);

        return "redirect:../adminPanel";
    }

}
