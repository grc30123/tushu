package com.example.tushu.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.tushu.entity.Shopping;
import com.example.tushu.service.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author grc
 * @since 2023-09-24
 */
@RestController
@RequestMapping("/shopping")
public class ShoppingController {
    @Autowired
    ShoppingService shoppingService;

    @GetMapping("/getList")
    public List<Shopping> getList() {
        return shoppingService.list();
    }

    @GetMapping("/getPageList")
    public Object getPageList(@RequestParam int size, @RequestParam int current) {
        Page<Shopping> page = shoppingService.page(new Page<>(current, size));
        return page;
    }

    @PostMapping("/insertShopping")
    public Object insertShopping(Shopping shopping) {
        boolean save = shoppingService.save(shopping);
        return save;
    }

}
