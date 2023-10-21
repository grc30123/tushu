package com.example.tushu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.tushu.entity.Orders;
import com.example.tushu.service.OrdersService;
import com.example.tushu.util.result;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author grc
 * @since 2023-09-27
 */
@RestController
@RequestMapping("/orders")
public class OrdersController {
    private final OrdersService ordersService;

    public OrdersController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @PostMapping("/GetList")
    public result GetList(@RequestParam int size, @RequestParam int current, @RequestBody Orders orders) {
        Page<Orders> page = ordersService.page(new Page<>(current, size), new QueryWrapper<Orders>().like("account", orders.getAccount()));
        List<Orders> list = page.getRecords();
        int total = ordersService.list().size();
        HashMap<Object, Object> res = new HashMap<>();
        res.put("list", list);
        res.put("total", total);
        return result.ok(res);
    }

    @GetMapping("/GetById")
    public result GetById(int id) {
        Orders res = ordersService.getById(id);
        return res == null ? result.err() : result.ok(res);
    }

    @ApiOperation("通过ID进行更改")
    @PostMapping("/SaveOrUpdate")
    public result SaveOrUpdate(@RequestBody Orders orders) {
        boolean res = ordersService.saveOrUpdate(orders);
        return res == false ? result.err() : result.ok(res);
    }

    @DeleteMapping("/DeleteById")
    public result Delete(int id) {
        boolean res = ordersService.removeById(id);
        return res == false ? result.err() : result.ok(res);
    }

}
