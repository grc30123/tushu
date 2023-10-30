package com.example.tushu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.tushu.entity.RoleResourceRelation;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author grc
 * @since 2023-10-29
 */
public interface RoleResourceRelationService extends IService<RoleResourceRelation> {

    int addBatch(List<RoleResourceRelation> list);
}
