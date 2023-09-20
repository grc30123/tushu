package com.example.tushu.service.impl;

import com.example.tushu.entity.User;
import com.example.tushu.mapper.UserMapper;
import com.example.tushu.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author grc
 * @since 2023-09-10
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
