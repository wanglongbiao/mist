package com.wanglongbiao.mist.auth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    //    private final UserDetailsServiceImpl userDetailsService;
//    private final DataSource dataSource;
//    private final PersistentTokenRepository persistentTokenRepository;

    //    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public PersistentTokenRepository persistentTokenRepository() {
//        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
//        jdbcTokenRepository.setDataSource(dataSource);
//        jdbcTokenRepository.setCreateTableOnStartup(false);
//        return jdbcTokenRepository;
//    }


//    @Override
//    @Bean
//    public AuthenticationManager authenticationManager() throws Exception {
//        return super.authenticationManagerBean();
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .anyRequest()
//                .authenticated()
//                .and().formLogin();
//                .loginPage("/login")// 自定义登录页面
//                .loginProcessingUrl("/login")// 对应 login.html 中表单的提交路径
//                .successForwardUrl("/");
//                .permitAll();// 登录成功后的跳转页面
//                .successHandler(new MyAuthenticationSuccessHandler("https://qq.com"));

        super.configure(http);

//        http.

        // 所有请求必须登录之后才能访问
//        http.authorizeRequests()
//                .antMatchers("/login").permitAll() // 放行登录页面，不然会无限 302
//                .antMatchers("/static/**").permitAll() // 放行登录页面，不然会无限 302
//                .antMatchers("/admin/**").hasAnyAuthority("admin") // 放行登录页面，不然会无限 302
//                .antMatchers("/admin/**").hasRole("role1") // 放行登录页面，不然会无限 302
//                .anyRequest().authenticated();// 一般放到最后
//
//        // 关闭 csrf，不然表单登录不进来
//        http.csrf().disable();

        // remember me
//        http.rememberMe()
//                .userDetailsService(userDetailsService)
//                .tokenRepository(persistentTokenRepository);
    }


    //    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
//        super.configure(auth);
//    }
    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("admin"));
        System.out.println(new BCryptPasswordEncoder().encode("user"));
    }
}
