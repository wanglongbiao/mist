package com.mist.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.Inet4Address;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@RestController
@RequiredArgsConstructor
public class IndexController {
    @Value("${server.port}")
    private int port;
    private String url = "http://mist-payment";

    private final RestTemplate restTemplate;

    @SneakyThrows
    @RequestMapping("/info")
    public String getIp() {
        return Inet4Address.getLocalHost().getHostAddress() + ":" + port;
    }

    @RequestMapping("/lb")
    public String loadBalance() {
        return restTemplate.getForObject(url + "/info", String.class);
    }
}
