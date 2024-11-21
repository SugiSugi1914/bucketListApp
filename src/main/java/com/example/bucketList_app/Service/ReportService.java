package com.example.bucketList_app.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bucketList_app.Domain.Report;
import com.example.bucketList_app.Repository.ReportRepository;

@Service
public class ReportService {
    @Autowired
    private ReportRepository reportRepository;

    public List<Report> findAllExceptionCategoryAndPriority() {
        return reportRepository.findAllExceptionCategoryAndPriority();
    }

    public Report findById(Integer id) {
        return reportRepository.findById(id);
    }

}
