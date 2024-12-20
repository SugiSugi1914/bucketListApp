package com.example.bucketList_app.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.bucketList_app.Domain.User;
import com.example.bucketList_app.Repository.UserRepository;
import com.example.bucketList_app.common.LoginUserDetails;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Integer id) {
        return userRepository.findById(id);
    }

    public void insert(User user) {
        String rawPassword = user.getPassword();
        String encodedPassword = passwordEncoder.encode(rawPassword);
        System.out.println("Raw password:" + rawPassword);
        System.out.println("Encoded password:" + encodedPassword);
        user.setPassword(encodedPassword);
        userRepository.insert(user);
    }

    public void update(User user) {
        if (!user.getPassword().equals(user.getPassword())) {
            // パスワードが変更されている場合はエンコードを行う
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        } else {
            // パスワードが変更されていない場合は既存のエンコード済みパスワードをそのまま使用する
            user.setPassword(user.getPassword());
        }
        userRepository.update(user);
    }

    public void updateExistEmail(User user) {
        userRepository.updateExistEmail(user);
    }


    public void delete(Integer id) {
        userRepository.delete(id);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}
