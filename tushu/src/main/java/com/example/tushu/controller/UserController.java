package com.example.tushu.controller;


import com.example.tushu.entity.User;
import com.example.tushu.mapper.RoleAuthorityMapper;
import com.example.tushu.service.RoleAuthorityService;
import com.example.tushu.service.UserService;
import com.example.tushu.util.result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author grc
 * @since 2023-09-10
 */
@RestController
@RequestMapping("/user")
@Api(value = "用户接口")
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    private final RoleAuthorityService roleAuthorityService;
    private final RoleAuthorityMapper roleAuthorityMapper;

    @GetMapping("/getinfo")
    public result getinfo() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        return authentication == null ? result.err() : result.ok(authentication);
    }


    @ApiOperation(value = "获取用户信息", notes = "根据ID获取")
    @GetMapping("/getbyid")
    public User get(int id) {
        User res = userService.getById(id);
        return res;
    }

    @ApiOperation(value = "id", notes = "测试")
    @PostMapping("/test")
//    @CrossOrigin(origins = "*")
    public result test(int ID_role) {
        List data = roleAuthorityMapper.getbyid(ID_role);
        return result.ok(data);
    }

//    @ApiOperation(value = "account password", notes = "登录")
//    @PostMapping("/login")
////    @CrossOrigin(origins = "*")
//    public result login(@RequestBody loginvo form) {
////        QueryWrapper condition = new QueryWrapper<>();
////        condition.eq("account", form.getAccount());
////        User user = userService.getOne(condition);
////        String res = user.getPassword();
////        if (res.equals(form.getPassword())) {
////            return result.ok(user);
////        }
//        return result.err();
//    }


}
