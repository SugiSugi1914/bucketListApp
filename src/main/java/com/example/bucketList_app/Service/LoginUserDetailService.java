package com.example.bucketList_app.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.bucketList_app.Domain.User;
import com.example.bucketList_app.Repository.LoginUserRepository;
import com.example.bucketList_app.common.LoginUserDetails;

@Service
public class LoginUserDetailService implements UserDetailsService {
    @Autowired
    private LoginUserRepository loginUserRepository;

    @Override
    public LoginUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> userOp = loginUserRepository.findByEmail(email);
        userOp.ifPresent(user -> {
            System.out.println("Loaded user: " + user.getEmail() + ", encoded password: " + user.getPassword());
        });
        return userOp.map(LoginUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

}