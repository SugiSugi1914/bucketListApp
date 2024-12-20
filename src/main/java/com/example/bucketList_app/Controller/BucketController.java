package com.example.bucketList_app.Controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.bucketList_app.Domain.Bucket;
import com.example.bucketList_app.Domain.Category;
import com.example.bucketList_app.Domain.Priority;
import com.example.bucketList_app.Form.BucketForm;
import com.example.bucketList_app.Service.BucketService;
import com.example.bucketList_app.Service.CategoryService;
import com.example.bucketList_app.Service.PriorityService;
import com.example.bucketList_app.common.LoginUserDetails;

@Controller
@RequestMapping("/bucket")
public class BucketController {

    @Autowired
    private BucketService bucketService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private PriorityService priorityService;

    @ModelAttribute
    public BucketForm setUpBucketForm() {
        return new BucketForm();
    }

    @RequestMapping("/toMyBucket")
    public String toMyBucket(@AuthenticationPrincipal LoginUserDetails LoginUserDetails, Model model) {
        List<Bucket> bucketList = bucketService.findAll();
        String name = LoginUserDetails.getUser().getName();
        model.addAttribute("bucketList", bucketList);
        model.addAttribute("name", name);
        return "bucket/myBucket";
    }

    @RequestMapping("/toCreation")
    public String toCreation() {
        return "bucket/creation";
    }

    @RequestMapping("/creation")
    public String creation(@Validated BucketForm form, BindingResult result,
            @AuthenticationPrincipal LoginUserDetails loginUserDetails, Model model) {
        if (result.hasErrors()) {
            List<Bucket> bucketList = bucketService.findAll();
            model.addAttribute("bucketList", bucketList);
            return "bucket/creation";
        }
        Bucket bucket = new Bucket();
        BeanUtils.copyProperties(form, bucket);

        String uploadDir = "src/main/resources/static/img/bucket/"; // 保存先ディレクトリ
        String defaultImage = "default.";

        try {
            if (form.getImage() != null && !form.getImage().isEmpty()) {
                // アップロードされた画像を保存
                String originalFilename = form.getImage().getOriginalFilename();
                Path filePath = Paths.get(uploadDir + originalFilename);
                Files.copy(form.getImage().getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
                bucket.setImage(originalFilename); // 保存した画像のファイル名を設定
            } else {
                // 画像がアップロードされなかった場合はデフォルト画像を設定
                bucket.setImage(defaultImage);
            }
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "画像の保存中にエラーが発生しました");
            return "bucket/creation";
        }
        // カテゴリーをドメインの型に変換
        Category category = categoryService.findById(form.getCategoryId());
        bucket.setCategory(category);
        bucket.setUser(loginUserDetails.getUser());
        // 優先度をドメインの型に変換
        Priority priority = priorityService.findById(form.getPriorityId());
        bucket.setPriority(priority);

        bucketService.insert(bucket);
        List<Bucket> bucketList = bucketService.findAll();
        model.addAttribute("bucketList", bucketList);
        return "redirect:/bucket/toMyBucket";
    }

    @RequestMapping("/toEditing")
    public String toEditing(Integer bucketId, Model model) {
        Bucket bucket = bucketService.findById(bucketId);
        BucketForm form = new BucketForm();
        BeanUtils.copyProperties(bucket, form);
        Integer categoryId = bucket.getCategory().getId();
        form.setCategoryId(categoryId);
        Integer priorityId = bucket.getPriority().getId();
        form.setPriorityId(priorityId);
        model.addAttribute("bucketForm", form);
        return "bucket/editing";
    }

    @RequestMapping("/editing")
    public String editing(@AuthenticationPrincipal LoginUserDetails loginUserDetails, Integer id, BucketForm form,
            Model model) {
        Bucket updateBucket = new Bucket();
        BeanUtils.copyProperties(form, updateBucket);
        updateBucket.setId(id);
        if (form.getImage() != null) {
            updateBucket.setImage(form.getImage().getOriginalFilename());
        }
        updateBucket.setUser(loginUserDetails.getUser());
        Category category = categoryService.findById(form.getCategoryId());
        updateBucket.setCategory(category);
        Priority priority = priorityService.findById(form.getPriorityId());
        updateBucket.setPriority(priority);
        bucketService.update(updateBucket);

        List<Bucket> bucketList = bucketService.findAll();
        model.addAttribute("bucketList", bucketList);
        return "bucket/myBucket";
    }
}
