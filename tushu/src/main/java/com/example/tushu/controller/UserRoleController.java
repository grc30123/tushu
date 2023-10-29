package com.example.tushu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.tushu.entity.UserRole;
import com.example.tushu.service.UserRoleService;
import com.example.tushu.util.result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
@Api("用户--角色 关联")
@RequestMapping("/user-role")
public class UserRoleController {
    @Autowired
    private UserRoleService userRoleService;

    @ApiOperation("添加用户ID--角色ID 关联")
    @PostMapping("/userRoleConnect")
    public result userRoleConnect(String idUser, String roleName) {
        String roleID = userRoleService.getRoleId(idUser, roleName);
        if (roleID.equals("角色不存在")) {
            return result.err("角色不存在");
        }
        if (roleID.equals("用户已经授权角色")) {
            return result.err("用户已经授权角色");
        }
        UserRole connect = new UserRole();
        connect.setIdUser(Integer.valueOf(idUser));
        connect.setIdRole(Integer.valueOf(roleID));
        return userRoleService.save(connect) ? result.ok() : result.err();
    }

    @ApiOperation("更新用户ID--角色ID 关联")
    @PostMapping("/updateIdRole")
    public result updateIdRole(UserRole userRole) {
        boolean res = userRoleService.update(userRole, new QueryWrapper<UserRole>().eq("ID_user", userRole.getIdUser()));
        return res == true ? result.ok(res) : result.err(res);
    }

}
