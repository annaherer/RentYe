package valuemakers.app.rentye.controller;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
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
import valuemakers.app.rentye.model.UserAccount;
import valuemakers.app.rentye.repository.UserAccountRepository;

import java.util.Collection;

@Controller
@RequestMapping("/admin")
@Secured("ROLE_ADMIN")
public class AdminController {
    private RentYeUserDetailsManager userDetailsManager;
    private ModelMapper modelMapper;
    private UserAccountRepository userAccountRepository;
    private PasswordEncoder passwordEncoder;

    public AdminController(RentYeUserDetailsManager userDetailsManager, ModelMapper modelMapper, UserAccountRepository userAccountRepository, PasswordEncoder passwordEncoder) {
        this.userDetailsManager = userDetailsManager;
        this.modelMapper = modelMapper;
        this.userAccountRepository = userAccountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @ModelAttribute("allUsers")
    public Collection<UserAccount> getAllUsers() {
        return userAccountRepository.findAll();
    }

    @GetMapping("/adminPanel")
    public String adminPanel() {
        return "adminPanel";
    }

    @GetMapping("/addUser")
    public String addUser(Model model) {
        UserAccountAdminDTO userAccountAdminDTO = new UserAccountAdminDTO();
        model.addAttribute("userAccountAdminDTO", userAccountAdminDTO);
        return "userAccountAddEdit";
    }

    @PostMapping("/addUser")
    public String addUserPost(@Valid @ModelAttribute UserAccountAdminDTO userAccountAdminDTO, BindingResult result) {
        userAccountAdminDTO.normalizeStringAttributes();

        if (userAccountAdminDTO.getNewPassword().isEmpty())
            result.addError(new FieldError("userAccountAdminDTO", "newPassword", "Password cannot be blank"));

        if (userAccountRepository.findByUsername(userAccountAdminDTO.getUsername()) != null)
            result.addError(new FieldError("userAccountAdminDTO", "username", "Username already exists"));

        if (userAccountRepository.findByEmail(userAccountAdminDTO.getEmail()) != null)
            result.addError(new FieldError("userAccountAdminDTO", "email", "Email already exists"));

        if (result.hasErrors()) {
            return "userAccountAddEdit";
        }

        userAccountAdminDTO.setPassword(passwordEncoder.encode(userAccountAdminDTO.getNewPassword()));

        userDetailsManager.createUser(new RentYeUserDetails(modelMapper.map(userAccountAdminDTO, UserAccount.class)));

        return "redirect:./adminPanel";
    }

    @GetMapping("/editUser/{userAccount}")
    public String editUser(@PathVariable UserAccount userAccount, Model model) {
        UserAccountAdminDTO userAccountAdminDTO = modelMapper.map(userAccount, UserAccountAdminDTO.class);
        model.addAttribute("userAccountAdminDTO", userAccountAdminDTO);
        return "userAccountAddEdit";
    }

    @PostMapping("/editUser/{id}")
    public String editUserPost(@Valid @ModelAttribute UserAccountAdminDTO userAccountAdminDTO, BindingResult result) {
        userAccountAdminDTO.normalizeStringAttributes();
        if (!userAccountAdminDTO.getNewPassword().isEmpty())
            userAccountAdminDTO.setPassword(passwordEncoder.encode(userAccountAdminDTO.getNewPassword()));

        UserAccount userAccountWithSameEmail = userAccountRepository.findByEmail(userAccountAdminDTO.getEmail());
        if (userAccountWithSameEmail != null && !userAccountWithSameEmail.getUsername().equals(userAccountAdminDTO.getUsername()))
            result.addError(new FieldError("userAccountAdminDTO", "email", "Email already exists"));

        if (SecurityContextHolder.getContext().getAuthentication().getName().equals(userAccountAdminDTO.getUsername()))
            result.addError(new ObjectError("userAccountAdminDTO", "You can't edit yourself in this panel, go to your account profile"));

        if (result.hasErrors()) {
            return "userAccountAddEdit";
        }

        userDetailsManager.updateUser(new RentYeUserDetails(modelMapper.map(userAccountAdminDTO, UserAccount.class)));

        return "redirect:../adminPanel";
    }

    @GetMapping("/deleteUser/{userAccount}")
    public String deleteUser(@PathVariable UserAccount userAccount, RedirectAttributes redirectAttributes) {
        if (SecurityContextHolder.getContext().getAuthentication().getName().equals(userAccount.getUsername()))
            redirectAttributes.addAttribute("message", "You cannot delete yourself");
        else
            userDetailsManager.deleteUser(userAccount.getUsername());

        return "redirect:../adminPanel";
    }

}
