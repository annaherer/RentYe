package valuemakers.app.rentye;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import valuemakers.app.rentye.dto.UserAccountDTO;
import valuemakers.app.rentye.repository.UserAccountRepository;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true)
public class RentYeWebSecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/", "/contact", "/about", "/WEB-INF/**", "/img/**", "/css/**", "/js/**").permitAll()
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults())
                .logout(httpSecurityLogoutConfigurer -> httpSecurityLogoutConfigurer.logoutSuccessUrl("/"));

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    RentYeUserDetailsManager users(PasswordEncoder passwordEncoder, UserAccountRepository userAccountRepository, ModelMapper modelMapper) {
        RentYeUserDetailsManager users = new RentYeUserDetailsManager(userAccountRepository, modelMapper);
        if (users.userCount() == 0) {
            UserAccountDTO admin = new UserAccountDTO();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("password"));
            admin.setEmail("admin@rentye.com");
            admin.setEnabled(true);
            admin.setAdmin(true);

            users.createUser(new RentYeUserDetails(admin));
        }
        return users;
    }
}