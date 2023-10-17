package com.example.tushu.config.SecurityConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.time.Duration;
import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    @Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    @Autowired
    private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
    @Autowired
    private CustomLogoutSuccessHandler customLogoutSuccessHandler;
    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/user/login").permitAll()
//                .antMatchers("/upLoad/**").permitAll()
                .antMatchers(HttpMethod.GET, // 允许对于网站静态资源的无授权访问
                        "/",
                        "/swagger-ui/",
                        "/*.html",
                        "/favicon.ico",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js",
                        "/swagger-resources/**",
                        "/v2/api-docs/**",
                        "/doc.html"
                )
                .permitAll()
                .anyRequest().authenticated()//所有请求拦截 authenticated经过验证的
//                .and()
//                .formLogin()
//                .loginProcessingUrl("/user/login") // 指定登录的路径为/login
//                .usernameParameter("account")
//                .passwordParameter("password")
//                //认证成功后再返回成功信息
//                .successHandler(customAuthenticationSuccessHandler)
//                .failureHandler(customAuthenticationFailureHandler)
//                .and()
//                .exceptionHandling()
//                .authenticationEntryPoint(customAuthenticationEntryPoint)
//                .and()
//                .logout()
////                .logoutRequestMatcher(new OrRequestMatcher(new AntPathRequestMatcher("/user/logout", "post")))
//                .logoutUrl("/user/logout")
//                .invalidateHttpSession(true) //注销登录 后清除 session
//                .clearAuthentication(true)  //注销登录 后 清除认证信息
//                .logoutSuccessHandler(customLogoutSuccessHandler)
                .and()
                // 在用户名密码认证过滤器之前添加令牌校验过滤器，用于拦截每个请求，校验请求头中是否携带有效的令牌，并将认证信息存入SecurityContext中
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .cors().configurationSource(corsConfigurationSource())//  打开 支持跨域访问
                .and()
                .csrf().disable()//               默认只有get请求可以通过认证 这句代码让所有请求都能通过认证
                .headers().cacheControl().disable()
                //关闭session登录验证
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        ;
        // 禁用缓存
        http.headers().cacheControl();
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

    //    验证密码是否正确
//    @Bean
//    public DaoAuthenticationProvider daoAuthenticationProvider() {
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setUserDetailsService(customUserDetailsService);
//        provider.setPasswordEncoder(passwordEncoder());
//        return provider;
//    }

    @Bean
    AuthenticationManager authenticationManager() {
        CustomAuthenticationProvider customAuthenticationProvider = new CustomAuthenticationProvider();
        customAuthenticationProvider.setUserDetailsService(customUserDetailsService);
        ProviderManager pm = new ProviderManager(customAuthenticationProvider);
        return pm;
    }

}
