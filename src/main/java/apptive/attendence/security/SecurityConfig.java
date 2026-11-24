package apptive.attendence.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorizeRequests ->authorizeRequests
                        .requestMatchers("/api/admin").hasRole("ADMIN")
                        .requestMatchers("/api/**").permitAll())
                .formLogin(formLogin ->
                        formLogin.loginPage("/login")
                                .defaultSuccessUrl("/", true)
                                .failureUrl("/login?error=true"));
        return http.build();
    }

    // 비밀번호 암호화 Bean
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
