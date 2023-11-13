package com.example.tushu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.tushu.entity.Menu;
import com.example.tushu.mapper.MenuMapper;
import com.example.tushu.service.MenuService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author grc
 * @since 2023-11-01
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

}
