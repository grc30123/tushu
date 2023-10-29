package com.example.tushu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.tushu.entity.User;
import com.example.tushu.entity.UserRole;
import com.example.tushu.mode.vo.UserInfo;
import com.example.tushu.service.RoleService;
import com.example.tushu.service.UserRoleService;
import com.example.tushu.service.UserService;
import com.example.tushu.util.JWT;
import com.example.tushu.util.result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private RoleService roleService;

    @ApiOperation("获取用户信息")
    @GetMapping("/getinfo")
    public result getinfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        com.example.tushu.mode.vo.User principal = (com.example.tushu.mode.vo.User) authentication.getPrincipal();
        long idUser = principal.getIdUser();
        String account = principal.getUsername();
        String role = principal.getRole();
        Collection<GrantedAuthority> authorities = principal.getAuthorities();
        UserInfo userInfo = new UserInfo(idUser, account, role, authorities);
        SecurityContext context = SecurityContextHolder.getContext();
        return authentication == null ? result.err() : result.ok(userInfo);
    }

    @ApiOperation(value = "获取用户信息", notes = "根据ID获取")
    @GetMapping("/GetById")
    public result GetById(int id) {
        User res = userService.getById(id);
        return res == null ? result.err() : result.ok(res);
    }

    @ApiOperation(value = "ID 删除用户", notes = "根据ID获取")
    @PostMapping("/DeleteById")
    public result DeleteById(int id) {
        boolean res = userService.removeById(id);
        return res == false ? result.err() : result.ok(res);
    }

    @ApiOperation("分页列表")
    @PostMapping("/GetList")
    public result GetList(@RequestParam("size") int size, @RequestParam("current") int current, @RequestBody User condition) {
        IPage<User> page = userService.page(new Page<>(current, size), new QueryWrapper<User>().like("account", condition.getAccount()));
        List<User> list = page.getRecords();
        int total = userService.list().size();
        HashMap<String, Object> res = new HashMap<>();
        res.put("total", total);
        res.put("list", list);
        return result.ok(res);
    }

    @PostMapping("/SaveOrUpdate")
    public result SaveOrUpdate(@RequestBody User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ID_user", user.getIdUser());//有ID则是更新 没有则是添加
        boolean res = userService.saveOrUpdate(user, queryWrapper);
        UserRole role = userRoleService.getId(user.getIdUser());//保存后，通过
        if (role == null) {
            UserRole userRole = new UserRole();
            userRole.setIdUser(Math.toIntExact(user.getIdUser()));
            userRole.setIdRole(4);
            userRoleService.save(userRole);
        }
        return res == false ? result.err() : result.ok(res);
    }

    @PostMapping("/Register")
    public result Register(@RequestBody User user) {
        boolean res = userService.save(user);
        return res == false ? result.err() : result.ok(res);
    }

    @PostMapping("/login")
    public result login(User user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getAccount(), user.getPassword())
        );
        if (authentication.getPrincipal() == null && authentication.getCredentials() == null) {
            return result.err("密码错误");
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        SecurityContext securityContext = SecurityContextHolder.getContext();
        // 生成一个JWT令牌，并设置过期时间和签名密钥
        String token = JWT.generateJWT(user.getAccount());
        Map<String, Object> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
//        tokenMap.put("securityContext", securityContext);
        return result.ok(tokenMap);
    }

    @GetMapping("/logout")
    public result logout() {
        SecurityContextHolder.clearContext();
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            return result.ok("已经成功注销");
        }
        return result.err(SecurityContextHolder.getContext().getAuthentication());
    }

    @ApiOperation("用户角色联查")
    @PostMapping("/getUserAndRole")
    public result getUserAndRole(@RequestParam("size") int size, @RequestParam("current") int current, @RequestBody User condition) {
//        @RequestParam("size") int size, @RequestParam("current") int current, @RequestBody User condition
        List<Object> list = userService.getUserAndRole(size, --current * size, condition);
        int total = userService.list().size();
        HashMap<String, Object> res = new HashMap<>();
        res.put("total", total);
        res.put("list", list);
        return result.ok(res);
    }

}
