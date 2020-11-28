package com.wanglongbiao.mist.auth.config;

import com.wanglongbiao.mist.auth.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//    @Autowired
//    private UserDetailsServiceImpl userService;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Override
//    @Bean
//    public AuthenticationManager authenticationManager() throws Exception {
//        return super.authenticationManagerBean();
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 使用表单登录
        http.formLogin()
                .loginProcessingUrl("/login")// 对应 login.html 中表单的提交路径
                .loginPage("/login");// 自定义登录页面

//        http.

        // 所有请求必须登录之后才能访问
//        http.authorizeRequests()
//                .antMatchers("/login").permitAll()
//                .anyRequest().authenticated();

        // 关闭 csrf，不然表单登录不进来
        http.csrf().disable();
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
//        super.configure(auth);
//    }
}
