package com.example.bucketList_app.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bucketList_app.Domain.Priority;
import com.example.bucketList_app.Repository.PriorityRepository;

@Service
public class PriorityService {
    
    @Autowired
    private PriorityRepository priorityRepository;

    public Priority findById(Integer id) {
        return priorityRepository.findById(id);
    }
}
