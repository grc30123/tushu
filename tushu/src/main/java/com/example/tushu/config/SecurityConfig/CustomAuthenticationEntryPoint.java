package com.example.tushu.config.SecurityConfig;

import com.alibaba.fastjson.JSON;
import com.example.tushu.util.result;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        // 设置响应状态码为401
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        // 设置响应内容类型为json
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        // 设置响应编码为utf-8
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        // 返回json格式的认证失败信息
        response.getWriter().write(JSON.toJSONString(result.err("请登录后访问")));
    }
}
