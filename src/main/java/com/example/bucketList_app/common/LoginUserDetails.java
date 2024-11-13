package com.example.bucketList_app.common;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.bucketList_app.Domain.User;

public class LoginUserDetails implements UserDetails {
    private final User user;
    private final Collection<? extends GrantedAuthority> authorities;

    public LoginUserDetails(User user) {
        this.user = user;
        this.authorities = Collections.singletonList(new SimpleGrantedAuthority(user.getRole()));
    }

    public User getUser() {
        return user;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override // ユーザーが期限切れでなければtrueを返す
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override // ユーザーがロックされていなければtrueを返す
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override // ユーザーのパスワードが期限切れでなければtrueを返す
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override // ユーザーが有効であればtrueを返す
    public boolean isEnabled() {
        return true;
    }

}
