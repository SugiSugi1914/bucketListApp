package com.example.bucketList_app.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bucketList_app.Domain.Category;
import com.example.bucketList_app.Repository.CategoryRepository;

@Service
public class CategoryService {
    
    @Autowired
    private CategoryRepository categoryRepository;
 
    public Category findById(Integer id) {
        return categoryRepository.findById(id);
    }
}
