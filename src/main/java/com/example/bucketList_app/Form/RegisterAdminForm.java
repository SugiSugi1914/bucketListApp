package com.example.bucketList_app.Form;

import jakarta.validation.constraints.NotBlank;

public class RegisterAdminForm {
    @NotBlank(message = "名前の入力は必須です")
    private String name;
    // @NotNull(message = "年齢の入力は必須です")
    private Integer age;
    @NotBlank(message = "メールアドレスの入力は必須です")
    private String email;
    @NotBlank(message = "パスワードの入力は必須です")
    private String password;
    // @NotBlank(message = "性別の入力は必須です")
    private String gender;

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

    @Override
    public String toString() {
        return "RegisterAdminForm [name=" + name + ", age=" + age + ", email=" + email + ", password=" + password
                + ", gender=" + gender + "]";
    }

}
