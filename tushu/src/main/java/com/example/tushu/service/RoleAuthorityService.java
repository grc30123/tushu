package com.example.tushu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.tushu.entity.RoleAuthority;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author grc
 * @since 2023-09-10
 */
public interface RoleAuthorityService extends IService<RoleAuthority> {

    int addBatch(List<RoleAuthority> roleAuthorities);
}
