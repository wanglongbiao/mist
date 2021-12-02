package com.wanglongbiao.mist.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/success")
    public String hi() {
        return "success page";
    }
    @GetMapping("/error")
    public String error() {
        return "error page";
    }
}
