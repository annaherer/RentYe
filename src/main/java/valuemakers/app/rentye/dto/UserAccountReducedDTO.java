package valuemakers.app.rentye.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UserAccountReducedDTO {
    private String firstName;
    private String lastName;
    @NotBlank
    @Email
    private String email;
    private Boolean admin;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public void normalizeStringAttributes() {
        firstName = firstName.trim();
        lastName = lastName.trim();
        email = email.trim().toLowerCase();
    }
}