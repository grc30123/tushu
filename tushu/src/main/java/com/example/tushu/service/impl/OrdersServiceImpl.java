package com.example.tushu.service.impl;

import com.example.tushu.entity.Orders;
import com.example.tushu.mapper.OrdersMapper;
import com.example.tushu.service.OrdersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author grc
 * @since 2023-09-27
 */
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersService {

}
