package valuemakers.app.rentye.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import valuemakers.app.rentye.model.UserAccount;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
    UserAccount findByUsername(String username);
    UserAccount findByEmail(String email);
}