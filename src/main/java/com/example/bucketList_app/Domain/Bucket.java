package com.example.bucketList_app.Domain;

import java.time.LocalDate;

public class Bucket {
    private Integer id;
    private String title;
    private String image;
    private Category category;
    private User user;
    private Integer budget;
    private LocalDate dueDate;
    private Priority priority;
    private String url;
    private String memo;
    private LocalDate creationDate;
    private Boolean achievement;
    private Boolean permission;
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public Integer getBudget() {
        return budget;
    }
    public void setBudget(Integer budget) {
        this.budget = budget;
    }
    public LocalDate getDueDate() {
        return dueDate;
    }
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
    public Priority getPriority() {
        return priority;
    }
    public void setPriority(Priority priority) {
        this.priority = priority;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getMemo() {
        return memo;
    }
    public void setMemo(String memo) {
        this.memo = memo;
    }
    public LocalDate getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
    public Boolean getAchievement() {
        return achievement;
    }
    public void setAchievement(Boolean achievement) {
        this.achievement = achievement;
    }
    public Boolean getPermission() {
        return permission;
    }
    public void setPermission(Boolean permission) {
        this.permission = permission;
    }

    @Override
    public String toString() {
        return "Bucket [id=" + id + ", title=" + title + ", image=" + image + ", category=" + category + ", user="
                + user + ", budget=" + budget + ", dueDate=" + dueDate + ", priority=" + priority + ", url=" + url
                + ", memo=" + memo + ", creationDate=" + creationDate + ", achievement=" + achievement + ", permission="
                + permission + "]";
    }
}
