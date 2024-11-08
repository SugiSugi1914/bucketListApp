package com.example.backetl_ist_app.domain;

public class Category {

    private Integer categoryId;
    private String category;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "CategoryDoamin [categoryId=" + categoryId + ", category=" + category + "]";
    }

    public Category() {

    }

    public Category(Integer categoryId, String category) {
        this.categoryId = categoryId;
        this.category = category;
    }

}
