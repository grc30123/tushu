package com.example.tushu.controller;


import cn.hutool.core.date.DateUtil;
import com.example.tushu.entity.Shopping;
import com.example.tushu.mode.dto.ShoppingLIst;
import com.example.tushu.service.ShoppingService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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
    public Object getPageList(@RequestParam int size, @RequestParam int current,
                              @RequestParam(required = false) Integer ID_user, @RequestParam(required = false) Integer flag) {
//        Page<Shopping> page = shoppingService.page(new Page<>(current, size));
        if (ID_user == null) {
            ID_user = -1;
            List<ShoppingLIst> list = shoppingService.getList(size, --current * size, ID_user, flag);
            int total = shoppingService.getTotal();
            HashMap<String, Object> res = new HashMap<>();
            res.put("data", list);
            res.put("total", total);
            return res;
        } else {
            List<ShoppingLIst> list = shoppingService.getList(size, --current * size, ID_user, flag);
            HashMap<String, Object> res = new HashMap<>();
            int total = shoppingService.getUserTotal(ID_user, flag);
            res.put("data", list);
            res.put("total", total);
            return res;
        }

    }

    @PostMapping("/insertShopping")
    public Object insertShopping(@RequestBody Shopping shopping) {
        shopping.setOrderDate(DateUtil.now());
        boolean save = shoppingService.save(shopping);
        return save;
    }

    @PostMapping("/deleteShopping")
    public Object deleteShopping(int shoppingId) {
        return shoppingService.removeById(shoppingId);
    }

    @ApiOperation("购物车结算")
    @PostMapping("/settlement")//购物结算，返回订单号，订单金额，订单日期，订单状态，订单商品信息，订单收货
    public Object settlement(@RequestBody List<Shopping> list) {
        list.forEach(shopping -> shopping.setOrderDate(DateUtil.now()));
        return shoppingService.updateBatchById(list);
    }

}
