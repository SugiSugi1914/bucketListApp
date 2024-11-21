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
                                                .loginPage("/toLogin")
                                                .loginProcessingUrl("/login")
                                                .defaultSuccessUrl("/bucket/toMyBucket")
                                                .failureUrl("/toLogin?error")
                                                .usernameParameter("email")
                                                .passwordParameter("password")
                                                .permitAll())
                                .logout(logout -> logout
                                                .logoutUrl("/logout")
                                                .logoutSuccessUrl("/user/toLogin")
                                                .clearAuthentication(true)
                                                .invalidateHttpSession(true))
                                .authorizeHttpRequests(authorize -> authorize
                                                .requestMatchers("/timeLine", "/img/**", "/login", "/toRegister",
                                                                "/register",
                                                                "/otherUsersBucket")
                                                .permitAll()
                                                .requestMatchers("/admin/**")
                                                .hasAuthority("Admin")
                                                .requestMatchers("/user/**")
                                                .hasAnyAuthority("User", "Admin")
                                                .anyRequest()
                                                .authenticated());

                return http.build();
        }
}