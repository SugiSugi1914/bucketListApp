package com.example.bucketList_app.Controller;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.bucketList_app.Domain.Report;
import com.example.bucketList_app.Domain.User;
import com.example.bucketList_app.Form.RegisterAdminForm;
import com.example.bucketList_app.Form.UpdateAdminForm;
import com.example.bucketList_app.Service.BucketService;
import com.example.bucketList_app.Service.ReportService;
import com.example.bucketList_app.Service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @ModelAttribute
    public RegisterAdminForm registerAdminForm() {
        return new RegisterAdminForm();
    }

    @ModelAttribute
    public UpdateAdminForm updateAdminForm() {
        return new UpdateAdminForm();
    }

    @Autowired
    UserService userService;

    @Autowired
    ReportService reportService;

    @Autowired
    BucketService bucketService;

    @RequestMapping("/dashboard")
    public String adminDashboard() {
        return "admin/dashboard";
    }

    @RequestMapping("/toRegister")
    public String toRegister(RegisterAdminForm form, Model model) {
        model.addAttribute("registerAdminForm", form);
        return "admin/inputInfo";
    }

    @RequestMapping("/register")
    public String register(@Validated RegisterAdminForm form, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "admin/inputInfo";
        }
        if (userService.findByEmail(form.getEmail()) != null) {
            model.addAttribute("emailError", "そのメールアドレスはすでに登録されています。");
            return "admin/inputInfo";
        }

        User user = new User();
        BeanUtils.copyProperties(form, user);
        user.setIcon("画像不要");
        user.setRole("Admin");
        userService.insert(user);
        return "redirect:/admin/dashboard";
    }

    @RequestMapping("/toReport")
    public String toReport(Model model) {
        List<Report> reportList = reportService.findAllExceptionCategoryAndPriority();
        model.addAttribute("reportList", reportList);
        return "admin/report";
    }

    @RequestMapping("/toDeleteReportUser")
    public String toDeleteReportUser(Model model) {
        List<Report> reportList = reportService.findAllExceptionCategoryAndPriority();

        List<User> suspicionUsers = reportList.stream()
                .map(Report::getSuspicionUser)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        Set<User> uniqueSuspicionUsers = reportList.stream()
                .map(Report::getSuspicionUser)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        model.addAttribute("suspicionUsers", suspicionUsers);

        // モデルに追加（重複なしの場合）
        model.addAttribute("uniqueSuspicionUsers", uniqueSuspicionUsers);

        // 管理画面のテンプレートを指定
        return "admin/delete";
    }

    @PostMapping("/deleteReportUser")
    public String deleteReportUser(@RequestParam("userId") Integer userId) {
        userService.delete(userId);
        return "redirect:/admin/toDeleteReportUser";
    }

    @RequestMapping("/toUpdateAdmin")
    public String toUpdateAdmin(@RequestParam("userId") Integer userId, Model model) {
        User user = userService.findById(userId);
        model.addAttribute("user", user);
        UpdateAdminForm form = new UpdateAdminForm();
        BeanUtils.copyProperties(user, form);
        user.setIcon("画像不要");
        user.setRole("Admin");
        model.addAttribute("updateAdminForm", form);
        return "admin/updateAdmin";
    }

    @RequestMapping("/updateAdmin")
    public String updateAdmin(@Validated UpdateAdminForm form, BindingResult result, Integer userId, Model model) {
        if (result.hasErrors()) {
            User user = userService.findById(userId);
            model.addAttribute("user", user);
            return "admin/updateAdmin";
        }
        if (userService.findByEmail(form.getEmail()) != null
                && userId != userService.findByEmail(form.getEmail()).getId()) {
            User user = userService.findById(userId);
            model.addAttribute("user", user);
            model.addAttribute("emailError", "このメールアドレスはすでに登録されています");
            return "admin/updateAdmin";
        }

        User user = new User();
        BeanUtils.copyProperties(form, user);
        user.setIcon("画像不要");
        user.setRole("Admin");
        userService.update(user);

        return "redirect:/admin/info";
    }

    @RequestMapping("/toDetail")
    public String toDetail(Integer reportId, Model model) {
        Report report = reportService.findById(reportId);
        model.addAttribute("report", report);
        return "report/detail";
    }

    @RequestMapping("/deleteReportBucket")
    public String deleteReportBucket(Integer reportBucketId, Model model) {

        bucketService.delete(reportBucketId);
        return "redirect:/admin/toReport";
    }
}
