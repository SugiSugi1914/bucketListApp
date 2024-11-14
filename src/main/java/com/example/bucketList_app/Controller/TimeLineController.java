package com.example.bucketList_app.Controller;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.bucketList_app.Domain.Bucket;
import com.example.bucketList_app.Service.BucketService;

@Controller
@RequestMapping("")
public class TimeLineController {
    
    @Autowired
    private BucketService bucketService;

    @RequestMapping("/top")
    public String top(Model model) {
        List<Bucket> bucketList = new ArrayList<>();
        bucketList = bucketService.findAllPermission();
        if(bucketList.size() == 0) {
            model.addAttribute("message", "自身の作成したバケットを投稿してみよう！");
        }
        model.addAttribute("bucketList", bucketList);
        return "top";
    }
}
