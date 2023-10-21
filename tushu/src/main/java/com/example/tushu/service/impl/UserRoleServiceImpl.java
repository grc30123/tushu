package com.example.tushu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.tushu.entity.Role;
import com.example.tushu.entity.UserRole;
import com.example.tushu.mapper.UserRoleMapper;
import com.example.tushu.service.RoleService;
import com.example.tushu.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author grc
 * @since 2023-09-10
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public String getRoleId(String IdUser, String roleName) {
        //通过用户ID查询用户角色
        UserRole idUser = getOne(new QueryWrapper<UserRole>().eq("ID_user", IdUser));
        if (idUser != null) {
            return "用户已经授权角色";
        }
        //通过角色名查询 该角色是否存在
        Role res = roleService.getOne(new QueryWrapper<Role>().eq("roleName", roleName));
        if (res == null) {
            return "角色不存在";
        }
        return String.valueOf(res.getIdRole());
    }

    @Override
    public UserRole getId(Long id_user) {
        return userRoleMapper.getById(id_user);
    }
}
