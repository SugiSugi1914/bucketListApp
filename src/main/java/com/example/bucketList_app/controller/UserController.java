package com.example.bucketList_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.bucketList_app.Domain.User;
import com.example.bucketList_app.Form.LoginForm;
import com.example.bucketList_app.common.LoginUserDetails;
import com.example.bucketList_app.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("")
public class UserController {
    @Autowired
    private HttpSession session;
    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String showTimeLine() {
        return "timeLine";
    }

    @RequestMapping("/toLogin")
    public String toLogin(LoginForm form, Model model, @RequestParam(value = "error", required = false) String error) {
        if (error != null) {
            model.addAttribute("loginError", "メールアドレス、もしくはパスワードが違います。");
        }
        return "user/login";
    }

    @RequestMapping("/myBucket")
    public String myBucket(@AuthenticationPrincipal LoginUserDetails loginUserDetails,
            Model model) {
        session.setAttribute("name", loginUserDetails.getUser().getEmail());
        if (loginUserDetails.getAuthorities() != null) {
            for (GrantedAuthority authority : loginUserDetails.getAuthorities()) {
                if (authority != null) {
                    if (authority.getAuthority().equals("admin")) {
                        return "redirect:/admin/top";
                    } else {
                        return "bucket/myBucket";
                    }
                }
            }
        }
        return "redirect:/toLogin";
    }
}
