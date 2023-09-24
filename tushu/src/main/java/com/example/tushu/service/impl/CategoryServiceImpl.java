package com.example.tushu.service.impl;

import com.example.tushu.entity.Category;
import com.example.tushu.mapper.CategoryMapper;
import com.example.tushu.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author grc
 * @since 2023-09-24
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

}
