package com.example.bucketList_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.bucketList_app.Domain.User;
import com.example.bucketList_app.Repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void insert(User user) {
        String rawPassword = user.getPassword();
        String encodedPassword = passwordEncoder.encode(rawPassword);
        System.out.println("Raw password:" + rawPassword);
        System.out.println("Encoded password:" + encodedPassword);
        user.setPassword(encodedPassword);
        userRepository.insert(user);
    }
}
