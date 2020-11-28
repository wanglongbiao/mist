package com.wanglongbiao.mist.auth.config;

import com.wanglongbiao.mist.auth.handler.MyAuthenticationSuccessHandler;
import com.wanglongbiao.mist.auth.service.UserDetailsServiceImpl;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private DataSource dataSource;
    @Autowired
    private PersistentTokenRepository persistentTokenRepository;

//    @Bean
//    private DataSource dataSource() {
////        return JdbcDat
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        jdbcTokenRepository.setCreateTableOnStartup(true);

        return jdbcTokenRepository;
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
                .loginPage("/login")// 自定义登录页面
                .loginProcessingUrl("/login")// 对应 login.html 中表单的提交路径
                .successForwardUrl("/");// 登录成功后的跳转页面
//                .successHandler(new MyAuthenticationSuccessHandler("https://qq.com"));


//        http.

        // 所有请求必须登录之后才能访问
        http.authorizeRequests()
                .antMatchers("/login").permitAll() // 放行登录页面，不然会无限 302
                .antMatchers("/static/**").permitAll() // 放行登录页面，不然会无限 302
                .antMatchers("/admin/**").hasAnyAuthority("admin") // 放行登录页面，不然会无限 302
                .antMatchers("/admin/**").hasRole("role1") // 放行登录页面，不然会无限 302
                .anyRequest().authenticated();// 一般放到最后

        // 关闭 csrf，不然表单登录不进来
        http.csrf().disable();

        // remember me
        http.rememberMe()
                .userDetailsService(userDetailsService)
                .tokenRepository(persistentTokenRepository);
    }


//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
//        super.configure(auth);
//    }
}
