package com.example.tushu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.tushu.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author grc
 * @since 2023-09-10
 */
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {

    UserRole getById(@Param("ID_user") Long ID_user);
}
