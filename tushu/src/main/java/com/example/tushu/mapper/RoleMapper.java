package com.example.tushu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.tushu.entity.Role;
import com.example.tushu.mode.dto.RoleAndResource;
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

    List<RoleAndResource> roleResourceList(String roleName, int current, int size);

    @Select(" SELECT COUNT(*)\n" +
            "    FROM role\n" +
            "             LEFT JOIN role_resource_relation ON role.ID_role = role_resource_relation.role_id\n" +
            "             LEFT JOIN resource ON role_resource_relation.resourse_id = resource.id\n" +
            "    WHERE role.roleName = IFNULL(#{roleName}, role.roleName);")
    int roleResourceCount(String roleName);

    @Select(" SELECT role.roleName,resource.* \n" +
            "    FROM role\n" +
            "             right JOIN role_resource_relation ON role.ID_role = role_resource_relation.role_id\n" +
            "             right JOIN resource ON role_resource_relation.resourse_id = resource.id\n")
    List<RoleAndResource> roleResourceListAll();
}
