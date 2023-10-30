package com.example.tushu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.tushu.entity.Resource;
import com.example.tushu.mapper.ResourceMapper;
import com.example.tushu.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author grc
 * @since 2023-10-29
 */
@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements ResourceService {
    @Autowired
    ResourceMapper resourceMapper;

    @Override
    public List<Map<String, String>> resourceList() {
        return resourceMapper.resourceList();
    }
    //根据url获取用户权限

}
