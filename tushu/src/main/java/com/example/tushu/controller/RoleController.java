package com.example.tushu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.tushu.entity.Role;
import com.example.tushu.service.impl.RoleServiceImpl;
import com.example.tushu.util.result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author grc
 * @since 2023-09-20
 */
@RestController
@Api("角色接口")
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleServiceImpl roleService;

    @ApiOperation("添加角色")
    @PostMapping("/roleInsert")
    public result roleInsert(Role role) {
        List<Role> listRole = roleService.list(new QueryWrapper<Role>().eq("RoleName", role.getRoleName()));
        if (listRole.size() >= 1) {
            return result.err("存在多个重名角色");
        }
        List<Role> list = roleService.list();
        Integer idRole = list.get(list.size() - 1).getIdRole();
        role.setIdRole(idRole + 1);
        return roleService.save(role) ? result.ok() : result.err();
    }

    @ApiOperation("更改角色")
    @PostMapping("/roleUpdate")
    public result roleUpdate(@RequestParam("newRoleName") String newRoleName, @RequestParam("oldRoleName") String oldRoleName) {
        List<Role> list = roleService.list(new QueryWrapper<Role>().eq("roleName", oldRoleName));
        if (list.size() > 1) {
            return result.err("存在多个重名角色");
        }
        Role role = new Role();
        role.setRoleName(newRoleName);
        boolean res = roleService.saveOrUpdate(role, new QueryWrapper<Role>().eq("roleName", oldRoleName));
        return res ? result.ok() : result.err();
    }

    @ApiOperation("删除角色")
    @PostMapping("/roleDelete")
    public result roleDelete(String id) {
        boolean res = roleService.removeById(id);
        return res ? result.ok() : result.err();
    }

    @ApiOperation("获取角色")
    @PostMapping("/roleGet")
    public result roleGet(String id) {
        Role res = roleService.getById(id);
        return result.ok(res);
    }

    @ApiOperation("获取角色列表")
    @PostMapping("/roleList")
    public result roleList() {
        List<Role> list = roleService.list();
        return result.ok(list);
    }

    @ApiOperation("分页列表")
    @PostMapping("/rolePageList")
    public result rolePageList(@RequestParam("size") int size, @RequestParam("current") int current, @RequestBody Role condition) {
        Page<Role> Page = roleService.page(new Page<>(current, size), new QueryWrapper<Role>().like("roleName", condition.getRoleName()));
        List<Role> roleList = Page.getRecords();
        int total = roleService.list().size();
        HashMap<String, Object> res = new HashMap<>();
        res.put("total", total);
        res.put("roleList", roleList);
        return result.ok(res);
    }

    @ApiOperation("角色资源列表:参数为空，返回全部，存在角色名参数，返回对应角色资源")
    @GetMapping("/roleResourceList")
    public result roleResourceList(String roleName) {
        List<Object> list = roleService.roleResourceList(roleName);
        return result.ok(list);
    }

}
