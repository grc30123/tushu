package com.example.tushu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.tushu.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author grc
 * @since 2023-09-20
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    @Select("select * from role where ID_role=#{id}")
    public Role getbyid(int id);

    List<Object> roleResourceList(String roleName);
}
