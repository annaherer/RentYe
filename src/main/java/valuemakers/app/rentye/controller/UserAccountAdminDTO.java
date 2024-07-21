package valuemakers.app.rentye.controller;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UserAccountAdminDTO extends UserAccountDTO {
    private Long id;
    @NotBlank
    private String username;
    private String password;
    @NotNull
    private Boolean enabled = true;
    private String newPassword = "";

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public void normalizeStringAttributes() {
        super.normalizeStringAttributes();
        this.username = this.username.trim().toLowerCase();
    }
}
