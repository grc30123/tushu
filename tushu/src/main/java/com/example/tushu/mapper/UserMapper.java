package com.example.tushu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.tushu.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
public interface UserMapper extends BaseMapper<User> {

    List<Object> getUserAndRole(int size, int current, @Param("condition") User condition);
}
