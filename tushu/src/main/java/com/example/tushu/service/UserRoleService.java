package com.example.tushu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.tushu.entity.UserRole;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author grc
 * @since 2023-09-10
 */
public interface UserRoleService extends IService<UserRole> {

    String getRoleId(String idUser, String roleName);

    UserRole getId(Long id_user);
}
