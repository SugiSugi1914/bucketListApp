package com.example.bucketList_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.bucketList_app.Domain.User;
import com.example.bucketList_app.Repository.UserRepository;
import com.example.bucketList_app.common.LoginUserDetails;

@Service
public class LoginUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User with email: " + email + " not found");
        }
        return new LoginUserDetails(user);
    }

}
