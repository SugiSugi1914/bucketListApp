package com.example.bucketList_app.Domain;

public class ReportCategory {
    private Integer id;
    private String reportCategory;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReportCategory() {
        return reportCategory;
    }

    public void setReportCategory(String reportCategory) {
        this.reportCategory = reportCategory;
    }

    @Override
    public String toString() {
        return "ReportCategory [id=" + id + ", reportCategory=" + reportCategory + "]";
    }

}
