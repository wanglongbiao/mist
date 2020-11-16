package com.wanglongbiao.mist.auth.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }
}
