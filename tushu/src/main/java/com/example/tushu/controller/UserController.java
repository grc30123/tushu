package com.example.tushu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.tushu.entity.User;
import com.example.tushu.mode.vo.UserInfo;
import com.example.tushu.service.UserService;
import com.example.tushu.util.result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
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

    @GetMapping("/getinfo")
    public result getinfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        com.example.tushu.mode.vo.User principal = (com.example.tushu.mode.vo.User) authentication.getPrincipal();
        long idUser = principal.getIdUser();
        String account = principal.getUsername();
        String role = principal.getRole();
        Collection<GrantedAuthority> authorities = principal.getAuthorities();
        UserInfo userInfo = new UserInfo(idUser, account, role, authorities);
        return authentication == null ? result.err() : result.ok(userInfo);
    }

    @ApiOperation(value = "获取用户信息", notes = "根据ID获取")
    @GetMapping("/GetById")
    public result GetById(int id) {
        User res = userService.getById(id);
        return res == null ? result.err() : result.ok(res);
    }

    @ApiOperation(value = "获取用户信息", notes = "根据ID获取")
    @PostMapping("/DeleteById")
    public result DeleteById(int id) {
        boolean res = userService.removeById(id);
        return res == false ? result.err() : result.ok(res);
    }

    @PostMapping("/GetList")
    public result GetList(@RequestParam("size") int size, @RequestParam("current") int current, @RequestBody User condition) {
        Page<User> objectPage = new Page<>(current, size);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (condition.getAccount() != null) {
            queryWrapper.like("account", condition.getAccount());
        }
        IPage<User> page = userService.page(objectPage, queryWrapper);
        List<User> res = page.getRecords();
        return res == null ? result.err() : result.ok(res);
    }

    @PostMapping("/SaveOrUpdate")
    public result SaveOrUpdate(@RequestBody User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ID_user", user.getIdUser());
        boolean res = userService.saveOrUpdate(user, queryWrapper);
        return res == false ? result.err() : result.ok(res);
    }

    @PostMapping("/Register")
    public result Register(@RequestBody User user) {
        boolean res = userService.save(user);
        return res == false ? result.err() : result.ok(res);
    }

    @PostMapping("/login")
    public result login(User user) {
//        String token = userService.login(user.getAccount(), user.getPassword());
//        if (token == null) {
//            result.err("用户名或密码错误");
//        }
//        Map<String, String> tokenMap = new HashMap<>();
//        tokenMap.put("token", token);

        return result.ok("tokenMap");
    }

}
