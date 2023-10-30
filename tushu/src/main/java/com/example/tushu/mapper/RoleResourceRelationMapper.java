package com.example.tushu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.tushu.entity.RoleResourceRelation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author grc
 * @since 2023-10-29
 */
@Mapper
public interface RoleResourceRelationMapper extends BaseMapper<RoleResourceRelation> {

    int addBatch(List<RoleResourceRelation> RoleResourceRelations);
}
