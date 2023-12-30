package com.example.tushu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.tushu.entity.Shopping;
import com.example.tushu.mode.dto.ShoppingLIst;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author grc
 * @since 2023-09-24
 */
public interface ShoppingService extends IService<Shopping> {

    List<ShoppingLIst> getList(int size, int current, int ID_user, Integer flag);

    int getTotal();

    int getUserTotal(Integer id_user, Integer flag);
}
