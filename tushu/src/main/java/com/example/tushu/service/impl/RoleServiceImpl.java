package com.example.tushu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.tushu.entity.Role;
import com.example.tushu.mapper.RoleMapper;
import com.example.tushu.mode.dto.RoleAndResource;
import com.example.tushu.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author grc
 * @since 2023-09-20
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<RoleAndResource> roleResourceList(String roleName, int current, int size) {
        List<RoleAndResource> list = roleMapper.roleResourceList(roleName, current, size);
        return list;
    }
}
