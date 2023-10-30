package com.example.tushu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.tushu.entity.RoleResourceRelation;
import com.example.tushu.mapper.RoleResourceRelationMapper;
import com.example.tushu.service.RoleResourceRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author grc
 * @since 2023-10-29
 */
@Service
public class RoleResourceRelationServiceImpl extends ServiceImpl<RoleResourceRelationMapper, RoleResourceRelation> implements RoleResourceRelationService {

    @Autowired
    private RoleResourceRelationMapper roleResourceRelationMapper;

    @Override
    public int addBatch(List<RoleResourceRelation> list) {
        return roleResourceRelationMapper.addBatch(list);
    }
}
