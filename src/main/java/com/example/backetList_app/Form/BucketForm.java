package com.example.backetList_app.Form;

import java.time.LocalDate;

import org.springframework.web.multipart.MultipartFile;

public class BucketForm {
    
    private String title;
    private MultipartFile image;
    private Integer categoryId;
    private Integer userId;
    private Integer budjet;
    private LocalDate dueDate;
    private Integer priorityId;
    private String url;
    private String memo;
    private LocalDate creationDate;
    private Boolean achevement;

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public MultipartFile getImage() {
        return image;
    }
    public void setImage(MultipartFile image) {
        this.image = image;
    }
    public Integer getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
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
    public Integer getPriorityId() {
        return priorityId;
    }
    public void setPriorityId(Integer priorityId) {
        this.priorityId = priorityId;
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
        return "BucketForm [title=" + title + ", image=" + image + ", categoryId=" + categoryId + ", userId=" + userId
                + ", budjet=" + budjet + ", dueDate=" + dueDate + ", priorityId=" + priorityId + ", url=" + url
                + ", memo=" + memo + ", creationDate=" + creationDate + ", achevement=" + achevement + "]";
    }
}
