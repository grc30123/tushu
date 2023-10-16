package com.example.tushu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.tushu.config.SecurityConfig.CustomUserDetailsService;
import com.example.tushu.entity.User;
import com.example.tushu.mapper.UserMapper;
import com.example.tushu.service.UserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author grc
 * @since 2023-09-10
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    //    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Override
    public String login(String account, String password) {
//
//        UserDetails userDetails = userDetailsService.loadUserByUsername(account);
//        if (userDetails.getPassword().equals(password)) {
//            return "密码不正确";
//        }
//        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        // 生成一个JWT令牌，并设置过期时间和签名密钥
//        String token = Jwts.builder()
//                .setSubject(userDetails.getUsername())
//                .setExpiration(new Date(System.currentTimeMillis() + 600 * 60 * 1000))//最小单位为毫秒
//                .signWith(SignatureAlgorithm.HS512, "secret")
//                .compact();
//        return token;
        return "";
    }
}
