package com.example.bucketList_app.Controller;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.bucketList_app.Domain.User;
import com.example.bucketList_app.Form.LoginForm;
import com.example.bucketList_app.Form.UserForm;
import com.example.bucketList_app.Service.UserService;
import com.example.bucketList_app.common.LoginUserDetails;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class UserController {
    @Autowired
    private HttpSession session;
    @Autowired
    private UserService userService;

    @ModelAttribute
    public UserForm setUpUserForm() {
        return new UserForm();
    }

    @RequestMapping("/toLogin")
    public String toLogin(LoginForm form, Model model, @RequestParam(value = "error", required = false) String error) {
        if (error != null) {
            model.addAttribute("loginError", "メールアドレス、もしくはパスワードが違います。");
        }
        return "user/login";
    }

    @RequestMapping("/toOthers")
    public String toMyPage(@AuthenticationPrincipal LoginUserDetails loginUserDetails, Model model) {
        User userInfo = userService.findById(loginUserDetails.getUser().getId()); 
        UserForm form = new UserForm();
        BeanUtils.copyProperties(userInfo, form);
        model.addAttribute("updateUserForm", form);
        return "user/others";
    }

    @RequestMapping("/updateUser")//hiddenでパスワード送っているのはセキュリティ的に良くない（開発者ツールで確認できるため）
    public String updateUser(UserForm form, Integer id, String password, @AuthenticationPrincipal LoginUserDetails loginUserDetails, Model model) {
        // if(result.hasErrors()) {
        //     return "user/others";
        // }
        User user = new User();
        BeanUtils.copyProperties(form, user);
        if(form.getIcon() != null) {
            String icon = form.getIcon().getOriginalFilename();
            user.setIcon(icon);
        }
        user.setId(id);
        user.setPassword(password);
        String role = "User";
        user.setRole(role);
        // System.out.println("結果!!!!!!!!!!!!!!!!" + form.toString());
        userService.insert(user);
        return "user/login";
    }

    @RequestMapping("/addNewUser")
    public String addNewUser(@Validated UserForm form, BindingResult result, Model model) {
        if(result.hasErrors()) {
            return "user/others";
        }
        User user = new User();
        BeanUtils.copyProperties(form, user);
        if(form.getIcon() != null) {
            String icon = form.getIcon().getOriginalFilename();
            user.setIcon(icon);
        }
        String role = "User";
        user.setRole(role);
        // System.out.println("結果!!!!!!!!!!!!!!!!" + form.toString());
        userService.insert(user);
        return"user/others";
    }




    
}