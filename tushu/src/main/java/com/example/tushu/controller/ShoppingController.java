package com.example.tushu.controller;


import com.example.tushu.entity.Shopping;
import com.example.tushu.service.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
