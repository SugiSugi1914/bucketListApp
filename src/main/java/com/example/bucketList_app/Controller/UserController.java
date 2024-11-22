package com.example.bucketList_app.Controller;

import java.io.File;
import java.io.FileInputStream;

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
import org.springframework.web.multipart.MultipartFile;

import com.example.bucketList_app.Domain.User;
import com.example.bucketList_app.Form.LoginForm;
import com.example.bucketList_app.Form.UpdateUserForm;
import com.example.bucketList_app.Form.UserForm;
import com.example.bucketList_app.Service.UserService;
import com.example.bucketList_app.common.LoginUserDetails;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private HttpSession session;
    @Autowired
    private UserService userService;

    @ModelAttribute
    public UserForm setUpUserForm() {
        return new UserForm();
    }

    @ModelAttribute
    public UpdateUserForm setUpUpdateUserForm() {
        return new UpdateUserForm();
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
        // if(userInfo.getIcon() != null) {
        // String iconPath = "src/main/resources/static/img/user/" + userInfo.getIcon();
        // File file = new File(iconPath);
        // FileInputStream input = new FileInputStream(file);
        // MockMultipartFile は開発用のもので、MultipartFile を直接作成する代替手段がないため、この方法が実質的に必要となる
        // MultipartFile icon = new MultipartFile(input);
        // form.setIcon(icon);
        // }
        model.addAttribute("updateUserForm", form);
        return "user/others";
    }

    @RequestMapping("/updateUser") // hiddenでパスワード送っているのはセキュリティ的に良くない（開発者ツールで確認できるため）
    public String updateUser(@Validated UpdateUserForm updateForm, BindingResult result, Integer id, String password,
            @AuthenticationPrincipal LoginUserDetails loginUserDetails, Model model) {
        if (result.hasErrors()) {
            User userInfo = userService.findById(loginUserDetails.getUser().getId());
            UserForm form = new UserForm();
            BeanUtils.copyProperties(userInfo, form);
            model.addAttribute("updateUserForm", form);
            return "user/others";
        }
        User user = new User();
        BeanUtils.copyProperties(updateForm, user);
        if (updateForm.getIcon() != null) {
            String icon = updateForm.getIcon().getOriginalFilename();
            user.setIcon(icon);
        }
        user.setId(id);
        user.setPassword(password);
        String role = "User";
        user.setRole(role);
        if (loginUserDetails.getUser().getEmail().equals(updateForm.getEmail())) {
            userService.updateExistEmail(user);
        } else {
            userService.update(user);
        }
        return "redirect:/user/toLogin";
    }

    @RequestMapping("/addNewUser")
    public String addNewUser(@Validated UserForm form, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "user/others";
        }
        User user = new User();
        BeanUtils.copyProperties(form, user);
        if (form.getIcon() != null) {
            String icon = form.getIcon().getOriginalFilename();
            user.setIcon(icon);
        }
        String role = "User";
        user.setRole(role);
        // System.out.println("結果!!!!!!!!!!!!!!!!" + form.toString());
        userService.insert(user);
        return "user/others";
    }

    @RequestMapping("/deleteAccount")
    public String deleteAccount(@AuthenticationPrincipal LoginUserDetails loginUserDetails) {
        Integer userId = loginUserDetails.getUser().getId();
        userService.delete(userId);
        session.invalidate();
        return "redirect:/user/toLogin";
    }

}