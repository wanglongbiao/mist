package com.wanglongbiao.mistplay.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.wanglongbiao.mistplay.mapper")
public class MyBatisPlusConfig {
//    @Bean
//    public MybatisSqlSessionFactoryBean sqlSessionFactory(){
//        return new MybatisSqlSessionFactoryBean();
//    }
}
