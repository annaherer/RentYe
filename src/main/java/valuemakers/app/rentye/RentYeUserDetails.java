package valuemakers.app.rentye;

import jakarta.validation.Valid;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import valuemakers.app.rentye.dto.UserAccountDTO;

import java.util.ArrayList;
import java.util.Collection;

public class RentYeUserDetails implements UserDetails {
    @Valid
    private UserAccountDTO userAccountDTO;

    public RentYeUserDetails(UserAccountDTO userAccountDTO) {
        this.userAccountDTO = userAccountDTO;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        if (userAccountDTO == null) {
            return null;
        }

        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        if (userAccountDTO.getAdmin()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }

        return authorities;
    }

    @Override
    public String getPassword() {
        return userAccountDTO.getPassword();
    }

    @Override
    public String getUsername() {
        return userAccountDTO.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return userAccountDTO.getEnabled();
    }

    public UserAccountDTO getUserAccountDTO() {
        return userAccountDTO;
    }
}
