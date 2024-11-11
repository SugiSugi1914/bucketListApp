package com.example.bucketList_app.Domain;

import java.util.List;

public class Report {
    private Integer id;
    private String report;
    private List<String> reportCategory;
    private Bucket reportBucket;
    private User reportUser;
    private User suspicionUser;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public List<String> getReportCategory() {
        return reportCategory;
    }

    public void setReportCategory(List<String> reportCategory) {
        this.reportCategory = reportCategory;
    }

    public Bucket getReportBucket() {
        return reportBucket;
    }

    public void setReportBucket(Bucket reportBucket) {
        this.reportBucket = reportBucket;
    }

    public User getReportUser() {
        return reportUser;
    }

    public void setReportUser(User reportUser) {
        this.reportUser = reportUser;
    }

    public User getSuspicionUser() {
        return suspicionUser;
    }

    public void setSuspicionUser(User suspicionUser) {
        this.suspicionUser = suspicionUser;
    }

    @Override
    public String toString() {
        return "Report [id=" + id + ", report=" + report + ", reportCategory=" + reportCategory + ", reportBucket="
                + reportBucket + ", reportUser=" + reportUser + ", suspicionUser=" + suspicionUser + "]";
    }

}
