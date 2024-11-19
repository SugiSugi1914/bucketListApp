package com.example.bucketList_app.Controller;

import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.bucketList_app.Domain.Bucket;
import com.example.bucketList_app.Domain.Category;
import com.example.bucketList_app.Domain.Priority;
import com.example.bucketList_app.Form.BucketForm;
import com.example.bucketList_app.Service.BucketService;
import com.example.bucketList_app.Service.CategoryService;
import com.example.bucketList_app.Service.PriorityService;
import com.example.bucketList_app.common.LoginUserDetails;

@Controller
@RequestMapping("/timeLine")
public class TimeLineController {
    
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

    @RequestMapping("/toTimeLine")
    public String toTimeLine(Model model) {
        List<Bucket> bucketList = new ArrayList<>();
        bucketList = bucketService.findAllPermission();
        if(bucketList.size() == 0) {
            model.addAttribute("message", "自身の作成したバケットを投稿してみよう！");
        }
        model.addAttribute("bucketList", bucketList);
        return "timeLine/timeLine";
    }

    @RequestMapping("/toAddition")
    public String toAddition(@AuthenticationPrincipal LoginUserDetails loginUserDetails, Integer id, Model model) {
        Bucket addBucket = bucketService.findById(id);
        BucketForm form = new BucketForm();
        BeanUtils.copyProperties(addBucket, form);
        //カテゴリー別の画像を別で用意しといてデフォはそれを入れとくか（画像変更するだろうから画像がnullにならず賑やかに見える）
        if(addBucket.getImage() != null) {
            addBucket.setImage(null);
        }
        Integer categoryId = addBucket.getCategory().getId();
        form.setCategoryId(categoryId);
        addBucket.setUser(loginUserDetails.getUser());
        Integer priorityId = addBucket.getPriority().getId();
        form.setPriorityId(priorityId);
        form.setCreationDate(LocalDate.now());
        model.addAttribute("bucketForm", form);
        return "timeLine/addition";
    }

    @RequestMapping("/addition")
    public String addition(@Validated BucketForm form, BindingResult result, @AuthenticationPrincipal LoginUserDetails loginUserDetails, 
                            Integer id, RedirectAttributes redirectAttributes, Model model) {
        if(result.hasErrors()) {
            Bucket addBucket = bucketService.findById(id);
            BucketForm formReturn = new BucketForm();
            BeanUtils.copyProperties(addBucket, formReturn);
            //カテゴリー別の画像を別で用意しといてデフォはそれを入れとくか（画像変更するだろうから画像がnullにならず賑やかに見える）
            if(addBucket.getImage() != null) {
                addBucket.setImage(null);
            }
            Integer categoryId = addBucket.getCategory().getId();
            form.setCategoryId(categoryId);
            addBucket.setUser(loginUserDetails.getUser());
            Integer priorityId = addBucket.getPriority().getId();
            form.setPriorityId(priorityId);
            form.setCreationDate(LocalDate.now());
            model.addAttribute("bucketForm", formReturn);
            return "timeLine/addition";
        }
        Bucket newBucket = new Bucket();
        BeanUtils.copyProperties(form, newBucket);
        if(newBucket.getImage() != null) {
            newBucket.setImage(form.getImage().getOriginalFilename());
        }
        Category category = categoryService.findById(form.getCategoryId());
        newBucket.setCategory(category);
        newBucket.setUser(loginUserDetails.getUser());
        Priority priority = priorityService.findById(form.getPriorityId());
        newBucket.setPriority(priority);
        bucketService.insert(newBucket);
        List<Bucket> bucketList = bucketService.findAll();
        redirectAttributes.addFlashAttribute("bucketList", bucketList);
        return "redirect:/bucket/toMyBucket";
    }
}
