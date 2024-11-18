package com.example.bucketList_app.Form;

import java.time.LocalDate;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class BucketForm {

    private Integer id;
    @NotNull(message="タイトルを入力してください")
    private String title;
    private MultipartFile image;
    @NotNull(message="カテゴリーを入力してください")
    private Integer categoryId;
    private Integer userId;
    @Min(value = 0, message = "予算は0以上でなければなりません")
    private Integer budget;
    @NotNull(message="達成予定日を入力してください")
    private LocalDate dueDate;
    @NotNull(message="優先度を選択してください")
    private Integer priorityId;
    private String url;
    private String memo;
    @NotNull(message="作成日の入力は必須です")
    private LocalDate creationDate;
    // private Boolean achievement;
    // private Boolean permission;
    
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
    // public Boolean getAchievement() {
    //     return achievement;
    // }
    // public void setAchievement(Boolean achievement) {
    //     this.achievement = achievement;
    // }
    // public Boolean getPermission() {
    //     return permission;
    // }
    // public void setPermission(Boolean permission) {
    //     this.permission = permission;
    // }
    @Override
    public String toString() {
        return "BucketForm [id=" + id + ", title=" + title + ", image=" + image + ", categoryId=" + categoryId
                + ", userId=" + userId + ", budget=" + budget + ", dueDate=" + dueDate + ", priorityId=" + priorityId
                + ", url=" + url + ", memo=" + memo + ", creationDate=" + creationDate + "]";
    }
}
