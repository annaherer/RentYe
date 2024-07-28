package valuemakers.app.rentye.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAccountReducedDTO {
    private String firstName;
    private String lastName;
    @NotBlank
    @Email
    private String email;
    private Boolean admin;

    public void normalizeStringAttributes() {
        firstName = firstName.trim();
        lastName = lastName.trim();
        email = email.trim().toLowerCase();
    }
}