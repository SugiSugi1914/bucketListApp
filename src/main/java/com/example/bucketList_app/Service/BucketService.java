package com.example.bucketList_app.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bucketList_app.Domain.Bucket;
import com.example.bucketList_app.Repository.BucketRepository;

@Service
public class BucketService {
    
    @Autowired
    private BucketRepository bucketRepository;

    public Bucket findById(Integer id) {
        return bucketRepository.findById(id);
    }

    public List<Bucket> findAll() {
        return bucketRepository.findAll();
    }
}
