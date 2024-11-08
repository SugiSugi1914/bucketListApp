package com.example.backetl_ist_app.domain;

public class Priority {
    private Integer priorityId;
    private String priority;

    public Integer getPriorityId() {
        return priorityId;
    }

    public void setPriorityId(Integer priorityId) {
        this.priorityId = priorityId;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "Priority [priorityId=" + priorityId + ", priority=" + priority + "]";
    }

    public Priority() {

    }

    public Priority(Integer priorityId, String priority) {
        this.priorityId = priorityId;
        this.priority = priority;
    }

}
