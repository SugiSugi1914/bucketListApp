package com.example.backetl_ist_app.domain;

import java.time.LocalDate;

public class Bucket {
    private Integer bucketId;
    private String title;
    private String image;
    private Category category;
    private User user;
    private Integer budjet;
    private LocalDate dueDate;
    private Priority priority;
    private String url;
    private String memo;
    private LocalDate creationDate;
    private Boolean achevement;

    public Integer getBucketId() {
        return bucketId;
    }

    public void setBucketId(Integer bucketId) {
        this.bucketId = bucketId;
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

    public Integer getBudjet() {
        return budjet;
    }

    public void setBudjet(Integer budjet) {
        this.budjet = budjet;
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

    public Boolean getAchevement() {
        return achevement;
    }

    public void setAchevement(Boolean achevement) {
        this.achevement = achevement;
    }

    @Override
    public String toString() {
        return "Bucket [bucketId=" + bucketId + ", title=" + title + ", image=" + image + ", category=" + category
                + ", user=" + user + ", budjet=" + budjet + ", dueDate=" + dueDate + ", priority=" + priority + ", url="
                + url + ", memo=" + memo + ", creationDate=" + creationDate + ", achevement=" + achevement + "]";
    }

    public Bucket() {

    }

    public Bucket(Integer bucketId, String title, String image, Category category, User user, Integer budjet,
            LocalDate dueDate, Priority priority, String url, String memo, LocalDate creationDate, Boolean achevement) {
        this.bucketId = bucketId;
        this.title = title;
        this.image = image;
        this.category = category;
        this.user = user;
        this.budjet = budjet;
        this.dueDate = dueDate;
        this.priority = priority;
        this.url = url;
        this.memo = memo;
        this.creationDate = creationDate;
        this.achevement = achevement;
    }

}
