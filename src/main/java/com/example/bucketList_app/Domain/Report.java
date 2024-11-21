package com.example.bucketList_app.Domain;

import java.util.Optional;

public class Report {
    private Integer id;
    private String report;
    private Integer reportCategoryId;
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

    public Integer getReportCategoryId() {
        return reportCategoryId;
    }

    public void setReportCategoryId(Integer reportCategoryId) {
        this.reportCategoryId = reportCategoryId;
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
        return "Report [id=" + id + ", report=" + report + ", reportCategoryId=" + reportCategoryId + ", reportBucket="
                + reportBucket + ", reportUser=" + reportUser + ", suspicionUser=" + suspicionUser + "]";
    }
    // public Optional<User> map(Object object) {
    // // TODO Auto-generated method stub
    // throw new UnsupportedOperationException("Unimplemented method 'map'");
    // }
}
