package com.project.ticketing_gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.project.ticketing_gateway.service.CustomUserDetailsService;


@Configuration
@EnableWebSecurity
public class SecurityConfig {


    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
    @Bean
    PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .apply(MyCustomDsl.customDsl())
                .flag(true).and()
                .authorizeRequests(requests -> requests
                        .requestMatchers("/", "/home", "/ticket", "/api/*").permitAll()
                )
                .exceptionHandling(handling -> handling.accessDeniedPage("/accessDeniedPage"))
                .authorizeRequests(requests -> requests
                        .requestMatchers("/userForm", "/submitUser", "/form", "/submitForm").hasAnyAuthority("ADMIN"))
                .formLogin(login -> login
                        .loginPage("/login")
                        .failureUrl("/login?error=true")
                        .defaultSuccessUrl("/welcome").permitAll())
                .logout(logout -> logout
                		.logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout=true")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll());
		
		return http.build();

    }
}
