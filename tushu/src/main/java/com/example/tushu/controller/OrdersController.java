package com.example.tushu.controller;


import cn.hutool.core.date.DateUtil;
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

    @PostMapping("/insertOrder")
    public result insertOrder(@RequestBody Orders orders) {
        orders.setOrderDate(DateUtil.now());
        boolean res = ordersService.save(orders);
        return res == false ? result.err() : result.ok(res);
    }

    @DeleteMapping("/DeleteById")
    public result Delete(int id) {
        boolean res = ordersService.removeById(id);
        return res == false ? result.err() : result.ok(res);
    }

    @GetMapping("getTotal")
    public result getTotal() {
        int total = ordersService.count();
        int successCount = ordersService.count(new QueryWrapper<Orders>().eq("paid", 1));//成功订单数
        int failCount = ordersService.count(new QueryWrapper<Orders>().eq("paid", 0));//失败订单数
        int waitCount = ordersService.count(new QueryWrapper<Orders>().eq("paid", 2));//等待完成订单数
        HashMap<String, Object> res = new HashMap<>();
        res.put("total", total);
        res.put("successCount", successCount);
        res.put("failCount", failCount);
        res.put("waitCount", waitCount);
        return result.ok();
    }

}
