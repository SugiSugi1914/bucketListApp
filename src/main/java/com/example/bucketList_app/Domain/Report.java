package com.example.bucketList_app.Domain;

public class Report {
    private Integer id;
    private String priority;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "Report [id=" + id + ", priority=" + priority + "]";
    }

    public Report() {

    }

    public Report(Integer id, String priority) {
        this.id = id;
        this.priority = priority;
    }

}
