package com.example.tushu.config.SecurityConfig;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {


    private CustomUserDetailsService userDetailsService;

    public CustomUserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    public void setUserDetailsService(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        // 自定义认证逻辑，例如，检查用户名和密码是否正确
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        if (userDetails != null && isPasswordValid(password, userDetails.getPassword())) {
            return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        } else {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    private boolean isPasswordValid(String rawPassword, String encodedPassword) {
        // 在实际应用中，您需要实现密码加密和验证逻辑
        // 这里仅作示例
        return rawPassword.equals(encodedPassword);
    }
}
