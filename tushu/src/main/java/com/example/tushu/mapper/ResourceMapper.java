package com.example.tushu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.tushu.entity.Resource;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author grc
 * @since 2023-10-29
 */
@Mapper
public interface ResourceMapper extends BaseMapper<Resource> {
    @MapKey("roleName")
    List<Map<String, String>> resourceList();
}
