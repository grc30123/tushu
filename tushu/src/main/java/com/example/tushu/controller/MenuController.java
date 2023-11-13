package com.example.tushu.controller;


import com.example.tushu.entity.Menu;
import com.example.tushu.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author grc
 * @since 2023-11-01
 */
@Api("菜单接口")
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @ApiOperation("获取全部列表")
    @PostMapping("/getList")
    public Object getList() {
        List<Menu> list = menuService.list();
        return list;
    }
}
