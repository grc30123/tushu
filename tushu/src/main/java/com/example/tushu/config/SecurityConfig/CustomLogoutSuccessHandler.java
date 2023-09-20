package com.example.tushu.config.SecurityConfig;

import com.alibaba.fastjson.JSON;
import com.example.tushu.util.result;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class CustomLogoutSuccessHandler implements org.springframework.security.web.authentication.logout.LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // 设置响应状态码为200
        response.setStatus(HttpServletResponse.SC_OK);
        // 设置响应内容类型为json
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        // 设置响应编码为utf-8
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        // 返回json格式的登出成功信息
        response.getWriter().write(JSON.toJSONString(result.ok("登出成功")));
    }
}
