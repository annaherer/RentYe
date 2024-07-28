package valuemakers.app.rentye.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAccountDTO extends UserAccountReducedDTO {
    private Long id;
    @NotBlank
    private String username;
    private String password;
    @NotNull
    private Boolean enabled = true;
    private String newPassword = "";

    @Override
    public void normalizeStringAttributes() {
        super.normalizeStringAttributes();
        this.username = this.username.trim().toLowerCase();
    }
}