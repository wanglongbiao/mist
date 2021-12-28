package com.wanglongbiao.mistplay.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.net.Inet4Address;

import lombok.SneakyThrows;

@RestController
public class IndexController {
    @SneakyThrows
    @RequestMapping("/ip")
    public String getIp() {
        return Inet4Address.getLocalHost().getHostAddress();
    }
}
