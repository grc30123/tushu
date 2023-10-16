package com.example.tushu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.tushu.entity.User;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author grc
 * @since 2023-09-10
 */
public interface UserService extends IService<User> {

    String login(String account, String password);
}
