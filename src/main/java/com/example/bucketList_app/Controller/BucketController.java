package com.example.bucketList_app.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.bucketList_app.Domain.Bucket;
import com.example.bucketList_app.Service.BucketService;

@Controller
@RequestMapping("/bucket")
public class BucketController {

    @Autowired
    private BucketService bucketService;
    
    @RequestMapping("/toMyBucket")
    public String toMyBucket(Model model) {
        List<Bucket> bucketList = bucketService.findAll();
        model.addAttribute("bucketList", bucketList);
        return "bucket/myBucket";
    }

    
    


}
