package com.example.tushu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.tushu.entity.Role;

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

    List<Object> roleResourceList(String roleName);
}
