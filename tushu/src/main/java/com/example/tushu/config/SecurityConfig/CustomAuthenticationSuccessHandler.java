package com.example.tushu.config.SecurityConfig;

import com.alibaba.fastjson.JSON;
import com.example.tushu.util.result;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class CustomAuthenticationSuccessHandler implements org.springframework.security.web.authentication.AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // 获取当前登录的用户信息
        Object principal = authentication.getPrincipal();
        User user = (User) principal;
        // 生成一个JWT令牌，并设置过期时间和签名密钥
        String token = Jwts.builder()
                .setSubject(user.getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + 600 * 60 * 1000))//最小单位为毫秒
                .signWith(SignatureAlgorithm.HS512, "secret")
                .compact();
        // 设置响应状态码为200
        response.setStatus(HttpServletResponse.SC_OK);
        // 设置响应内容类型为json
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        // 设置响应编码为utf-8
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        // 返回json格式的认证成功信息，包含令牌和用户信息
        response.getWriter().write(JSON.toJSONString(result.ok(new LoginInfo(token, user))));
    }
}
