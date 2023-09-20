package com.example.test_learn.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("login")
    public String login() {
        return "登录成功";
    }

    @GetMapping("info")
    public String info() {
        return "信息";
    }
}
