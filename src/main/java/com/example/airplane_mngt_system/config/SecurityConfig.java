package com.example.airplane_mngt_system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest().permitAll()  // Allow all requests (optional for H2 console)
                .and()
                .csrf().disable();  // Disable CSRF (optional for the console and development environments)

        // Allow frames from the same origin
        http.headers().frameOptions().sameOrigin();

        return http.build();
    }
}
