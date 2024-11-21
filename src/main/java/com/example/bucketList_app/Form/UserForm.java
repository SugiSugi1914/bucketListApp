package com.example.bucketList_app.Form;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;

public class UserForm {
    private Integer id;
    @NotBlank(message="名前を入力してください")
    private String name;
    private MultipartFile icon;
    private Integer age;
    @NotBlank(message="メールアドレスの入力は必須です")
    private String email;
    @NotBlank(message="パスワードの入力は必須です")
    private String password;
    private String gender;
    private String role;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MultipartFile getIcon() {
        return icon;
    }

    public void setIcon(MultipartFile icon) {
        this.icon = icon;
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

    @Override
    public String toString() {
        return "UserForm [id=" + id + ", name=" + name + ", icon=" + icon + ", age=" + age + ", email=" + email
                + ", password=" + password + ", gender=" + gender + ", role=" + role + "]";
    }
}
