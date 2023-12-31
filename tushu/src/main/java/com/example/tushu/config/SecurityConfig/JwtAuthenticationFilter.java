package com.example.tushu.config.SecurityConfig;


import com.example.tushu.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//这是认证最初的一步，也是最核心的部分
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 获取请求头中的Authorization字段


        String header = request.getHeader("Authorization");
        // 如果请求头中有Authorization字段，并且以Bearer开头，则尝试解析令牌
        if (header != null && header.startsWith("Bearer.")) {
            try {
                // 截取令牌字符串并解析
                String token = header.substring(7);
                Claims claims = Jwts.parser()
                        .setSigningKey("secret")
                        .parseClaimsJws(token)
                        .getBody();
                // 获取用户名

                String username = claims.getSubject();
                // 如果用户名不为空，并且SecurityContext中没有认证信息，则进行认证
                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    // 根据用户名加载用户信息和权限
                    UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
                    // 创建一个UsernamePasswordAuthenticationToken对象，用于存放认证信息
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, null);
                    // 设置当前请求的详情，例如IP地址等
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    // 将认证信息存入SecurityContext中，表示当前用户已认证通过
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    SecurityContext context = SecurityContextHolder.getContext();
                    SecurityContext context1 = context;
                }
            } catch (Exception e) {
                // 如果令牌解析失败，则清空SecurityContext中的认证信息，表示当前用户未认证
                SecurityContextHolder.clearContext();
            }
        }
        // 继续执行后续的过滤器链
        filterChain.doFilter(request, response);
    }
}
