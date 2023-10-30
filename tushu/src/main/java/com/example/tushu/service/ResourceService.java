package com.example.tushu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.tushu.entity.Resource;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author grc
 * @since 2023-10-29
 */
public interface ResourceService extends IService<Resource> {
    List<Map<String, String>> resourceList();
}
