package com.example.tushu.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author grc
 * @since 2023-09-10
 */
@RestController
//@RequestMapping("/authority")
public class AuthorityController {

    @GetMapping("/lanjie")
    public String lanjie() {
        return "拦截";
    }

    @GetMapping("/fangxing")
    public String fangxing() {
        return "放行";
    }
}
