package com.example.bucketList_app.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .formLogin(login -> login
                        .loginProcessingUrl("/login")
                        .loginPage("/toLogin")
                        .defaultSuccessUrl("/myBucket")
                        .failureUrl("/toLogin?error")
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .permitAll())
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/toLogin")
                        .clearAuthentication(true)
                        .invalidateHttpSession(true))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/timeLine", "/img/**", "/login", "/toRegister", "/register",
                                "/otherUsersBucket")
                        .permitAll()
                        .requestMatchers("/admin/**")
                        .hasRole("admin")
                        .requestMatchers("/user/**")
                        .hasAnyRole("user", "admin")
                        .anyRequest()
                        .authenticated());

        return http.build();
    }
}