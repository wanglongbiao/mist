package com.wanglongbiao.mistplay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.Inet4Address;

import lombok.SneakyThrows;

@SpringBootApplication
public class MistPlayApplication {

    public static void main(String[] args) {
        SpringApplication.run(MistPlayApplication.class, args);
    }


    @SneakyThrows
    @RequestMapping("/ip2")
    @ResponseBody
    public String ipInfo() {
        return Inet4Address.getLocalHost().getHostAddress();
    }

}
