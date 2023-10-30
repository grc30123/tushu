package com.example.tushu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.tushu.entity.RoleAuthority;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author grc
 * @since 2023-09-10
 */
@Mapper
public interface RoleAuthorityMapper extends BaseMapper<RoleAuthority> {

    @Select("select * from role_authority where ID_role = #{id} ")
    public List<RoleAuthority> getbyid(int id);

    int addBatch(List<RoleAuthority> roleAuthorities);
}
