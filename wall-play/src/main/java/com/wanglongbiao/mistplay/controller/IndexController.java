package com.wanglongbiao.mistplay.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.Inet4Address;

import lombok.SneakyThrows;

@RestController
public class IndexController {
    @Value("${server.port}")
    private int port;

    @SneakyThrows
    @RequestMapping("/info")
    public String getIp() {
        return Inet4Address.getLocalHost().getHostAddress() + ":" + port;
    }
}
