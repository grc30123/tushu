package com.example.tushu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.tushu.entity.RoleAuthority;
import com.example.tushu.mapper.RoleAuthorityMapper;
import com.example.tushu.service.RoleAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author grc
 * @since 2023-09-10
 */
@Service
public class RoleAuthorityServiceImpl extends ServiceImpl<RoleAuthorityMapper, RoleAuthority> implements RoleAuthorityService {
    @Autowired
    private RoleAuthorityMapper roleAuthorityMapper;

    @Override
    public int addBatch(List<RoleAuthority> roleAuthorities) {
        return roleAuthorityMapper.addBatch(roleAuthorities);
    }
}
