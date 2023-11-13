package com.example.tushu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.tushu.config.SecurityConfig.component.DynamicSecurityMetadataSource;
import com.example.tushu.entity.Resource;
import com.example.tushu.entity.Role;
import com.example.tushu.entity.RoleResourceRelation;
import com.example.tushu.mapper.RoleMapper;
import com.example.tushu.mode.dto.RoleAndResource;
import com.example.tushu.service.ResourceService;
import com.example.tushu.service.RoleResourceRelationService;
import com.example.tushu.service.impl.RoleServiceImpl;
import com.example.tushu.util.result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

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
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private ResourceService resourceService;

    @Autowired
    private RoleResourceRelationService roleResourceRelationService;
    @Autowired
    private DynamicSecurityMetadataSource dynamicSecurityMetadataSource;

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
    public Object roleResourceList(@RequestParam String roleName, @RequestParam int current, @RequestParam int size) {
        //用户资源
        List<RoleAndResource> roleResourceList = roleService.roleResourceList(roleName, --current * size, size);
        List<Resource> ResourceList = resourceService.list();//全部资源
        List<Resource> unmatchedResources = ResourceList.stream()//该用户未分配资源
                .filter(resource -> roleResourceList.stream().noneMatch(roleResource -> roleResource.getUrl().equals(resource.getUrl())))
                .collect(Collectors.toList());
        int total = roleMapper.roleResourceCount(roleName);
        int unmatchedTotal = unmatchedResources.size();
        HashMap<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("unmatchedTotal", unmatchedTotal);
        map.put("data", roleResourceList);
        map.put("unmatchedResources", unmatchedResources);
        return map;
    }

    @ApiOperation("资源列表 所有")
    @GetMapping("/ResourceListAll")
    public Object ResourceListAll() {
        List<Resource> list = resourceService.list();
        HashMap<String, Object> map = new HashMap<>();
        map.put("total", list.size());
        map.put("data", list);
        return map;
    }

    @ApiOperation("角色资源列表 所有")
    @GetMapping("/roleResourceListAll")
    public Object roleResourceListAll() {
        List<RoleAndResource> list = roleMapper.roleResourceListAll();
        HashMap<String, Object> map = new HashMap<>();
        map.put("total", list.size());
        map.put("data", list);
        return map;
    }

    @ApiOperation("批量添加更改角色资源链接")
    @PostMapping("/roleResourceConnectList")
    public Object roleResourceConnectList(List<Role> list) {
        return roleService.saveOrUpdateBatch(list);
    }

    @ApiOperation("添加角色资源链接")
    @PostMapping("/roleResourceConnect")
    public Object roleResourceConnect(RoleResourceRelation connect) {
        dynamicSecurityMetadataSource.clearDataSource();
        return roleResourceRelationService.save(connect);
    }

    @ApiOperation("删除角色资源链接")
    @DeleteMapping("/roleResourceDeleteById")
    public Object roleResourceDelete(int id) {
        dynamicSecurityMetadataSource.clearDataSource();
        return roleResourceRelationService.removeById(id);
    }
}
