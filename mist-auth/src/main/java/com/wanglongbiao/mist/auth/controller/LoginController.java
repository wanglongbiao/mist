package com.wanglongbiao.mist.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class LoginController {

    @PostMapping("/")
    public String index() {
        return "redirect:index";
    }
}
