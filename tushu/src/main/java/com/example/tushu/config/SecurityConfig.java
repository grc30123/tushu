package com.example.tushu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.time.Duration;
import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/user/**").permitAll()
                .anyRequest().authenticated()//所有请求拦截 authenticated经过验证的
                .and().formLogin()
                .and()
                .cors().configurationSource(corsConfigurationSource())//  打开 支持跨域访问
                .and()
                .csrf().disable()//               默认只有get请求可以通过认证 这句代码让所有请求都能通过认证
                .headers().cacheControl().disable()
        ;
        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        // 跨域 对象
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration configuration = new CorsConfiguration();
        // 跨域 具体配置
        configuration.setAllowCredentials(false); // 允许携带 cookies
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setMaxAge(Duration.ofHours(1)); // 预检请求缓存时间为 1 小时
        source.registerCorsConfiguration("/**", configuration); // 对所有路径注册跨域配置
        return source;
    }


}
