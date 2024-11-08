package com.example.backetl_ist_app.domain;

public class Priority {
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
        return "Priority [id=" + id + ", priority=" + priority + "]";
    }

    public Priority() {

    }

    public Priority(Integer id, String priority) {
        this.id = id;
        this.priority = priority;
    }

}
