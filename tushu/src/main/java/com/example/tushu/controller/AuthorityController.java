package com.example.tushu.controller;


import com.example.tushu.entity.User;
import com.example.tushu.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
@AllArgsConstructor
//@RequestMapping("/authority")
public class AuthorityController {

    private UserService userService;

    @GetMapping(value = "/lanjie", produces = "application/json")
    public User lanjie() {

        User res = userService.getById(3);
        return res;
    }

    @PostMapping(value = "/fangxing", produces = "application/json")
    public String fangxing() {
        return "放行fadgagg搞就搞";
    }
}
