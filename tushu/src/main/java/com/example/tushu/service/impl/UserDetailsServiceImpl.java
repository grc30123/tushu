//package com.example.tushu.service.impl;
//
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.example.tushu.entity.User;
//import com.example.tushu.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//    @Autowired
//    private UserService userService;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//
//        QueryWrapper<User> wrapper = new QueryWrapper<>();
//        wrapper.eq("account", username);
//        User user = userService.getOne(wrapper);
//
//        if (user == null) {
//            throw new UsernameNotFoundException("用户不存在");
//        }
//
//        // 权限设置
//        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//        if ("admin".equals(user.getAccount())) {
//            authorities.add(new SimpleGrantedAuthority("admin"));
//        } else {
//            authorities.add(new SimpleGrantedAuthority("user"));
//        }
//
//        return new User(user.getAccount(), user.getPassword(), authorities);
//    }
//
//}