package com.example.backetl_ist_app.domain;

public class User {
    private Integer userId;
    private String name;
    private Integer age;
    private String email;
    private String password;
    private String gender;
    private String role;
    private String icon;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "User [userId=" + userId + ", name=" + name + ", age=" + age + ", email=" + email + ", password="
                + password + ", gender=" + gender + ", role=" + role + ", icon=" + icon + "]";
    }

    public User() {

    }

    public User(Integer userId, String name, Integer age, String email, String password, String gender, String role,
            String icon) {
        this.userId = userId;
        this.name = name;
        this.age = age;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.role = role;
        this.icon = icon;
    }

}
