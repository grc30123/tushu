package com.example.tushu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.tushu.entity.Role;
import com.example.tushu.mode.dto.RoleAndResource;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author grc
 * @since 2023-09-20
 */
public interface RoleService extends IService<Role> {

    List<RoleAndResource> roleResourceList(String roleName, int current, int size);
}
