package valuemakers.app.rentye;

import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import valuemakers.app.rentye.model.UserAccount;
import valuemakers.app.rentye.repository.UserAccountRepository;

import java.util.Collection;

public class RentYeUserDetailsManager implements UserDetailsManager {
    UserAccountRepository userAccountRepository;
    ModelMapper modelMapper;

    public RentYeUserDetailsManager(UserAccountRepository userAccountRepository, ModelMapper modelMapper) {
        this.userAccountRepository = userAccountRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void createUser(UserDetails user) {
        UserAccount userAccount = modelMapper.map(((RentYeUserDetails) user).getUserAccountDTO(), UserAccount.class);
        userAccount.setId(null);
        userAccountRepository.save(userAccount);
    }

    @Override
    public void updateUser(UserDetails user) {
        UserAccount userAccount = modelMapper.map(((RentYeUserDetails) user).getUserAccountDTO(), UserAccount.class);
        if (userAccount.getId() != null)
            userAccountRepository.save(userAccount);
    }

    @Override
    public void deleteUser(String username) throws UsernameNotFoundException {
        UserAccount userAccount = userAccountRepository.findByUsername(username);

        if (userAccount != null)
            userAccountRepository.delete(userAccount);
        else
            throw new UsernameNotFoundException("User not found");
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String username = authentication.getName();
            UserAccount userAccount = userAccountRepository.findByUsername(username);
            if (userAccount != null) {
                userAccount.setPassword(newPassword);
                userAccountRepository.save(userAccount);
            }
        }
    }

    @Override
    public boolean userExists(String username) {
        return (userAccountRepository.findByUsername(username) != null);
    }

    @Override
    public RentYeUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccount userAccount = userAccountRepository.findByUsername(username);

        if (userAccount != null)
            return userAccountToUserDetails(userAccount);
        else
            throw new UsernameNotFoundException("User not found");
    }

    public boolean userEmailExists(String email) {
        return (userAccountRepository.findByEmail(email) != null);
    }

    public RentYeUserDetails loadUserByEmail(String email) throws UsernameNotFoundException {
        UserAccount userAccount = userAccountRepository.findByEmail(email);

        if (userAccount != null)
            return userAccountToUserDetails(userAccount);
        else
            throw new UsernameNotFoundException("User not found");
    }

    public RentYeUserDetails loadUserById(Long id) throws UsernameNotFoundException {
        UserAccount userAccount = userAccountRepository.findById(id).orElse(null);

        if (userAccount != null)
            return userAccountToUserDetails(userAccount);
        else
            throw new UsernameNotFoundException("User not found");
    }

    public Collection<RentYeUserDetails> loadAllUsers() {
        return userAccountRepository.findAll().stream().map(this::userAccountToUserDetails).toList();
    }

    public long userCount() {
        return userAccountRepository.count();
    }

    private RentYeUserDetails userAccountToUserDetails(UserAccount userAccount) {
        return new RentYeUserDetails(modelMapper.map(userAccount, UserAccountDTO.class));
    }
}
