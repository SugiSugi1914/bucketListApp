package com.example.backetList_app.Form;

public class ReportForm {
    
    private Integer reportId;
    private String reportCategory;
    private Integer reportBucketId;
    private Integer reportUserId;
    
    public Integer getReportId() {
        return reportId;
    }
    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }
    public String getReportCategory() {
        return reportCategory;
    }
    public void setReportCategory(String reportCategory) {
        this.reportCategory = reportCategory;
    }
    public Integer getReportBucketId() {
        return reportBucketId;
    }
    public void setReportBucketId(Integer reportBucketId) {
        this.reportBucketId = reportBucketId;
    }
    public Integer getReportUserId() {
        return reportUserId;
    }
    public void setReportUserId(Integer reportUserId) {
        this.reportUserId = reportUserId;
    }
    
    @Override
    public String toString() {
        return "ReportForm [reportId=" + reportId + ", reportCategory=" + reportCategory + ", reportBucketId="
                + reportBucketId + ", reportUserId=" + reportUserId + "]";
    }
}
