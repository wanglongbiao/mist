package com.wanglongbiao.mist.admin.entity;

import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

@Data
public class User implements UserDetails {
    private Long id;
    private String username;
    private String password;
    private List<Role> authorities;

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
