package com.example.tushu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.tushu.entity.Shopping;
import com.example.tushu.mapper.ShoppingMapper;
import com.example.tushu.mode.dto.ShoppingLIst;
import com.example.tushu.service.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author grc
 * @since 2023-09-24
 */
@Service
public class ShoppingServiceImpl extends ServiceImpl<ShoppingMapper, Shopping> implements ShoppingService {

    @Autowired
    ShoppingMapper shoppingMapper;

    @Override
    public List<ShoppingLIst> getList(int size, int current, int ID_user, Integer flag) {
        List<ShoppingLIst> list = shoppingMapper.getList(size, current, ID_user, flag);
        return list;
    }

    @Override
    public int getTotal() {
        return shoppingMapper.getTotal();
    }

    @Override
    public int getUserTotal(Integer id_user, Integer flag) {
        return shoppingMapper.getUserTotal(id_user, flag);
    }
}
