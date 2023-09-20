package com.example.tushu.config.SecurityConfig;

import com.example.tushu.entity.User;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

// 使用@Data注解，自动生成getter和setter方法
@Data
// 定义一个名为LoginInfo的类，实现Serializable接口，用于序列化和反序列化
public class LoginInfo implements Serializable {

    // 定义一个常量，表示序列化版本号
    private static final long serialVersionUID = 1L;

    // 定义一个字符串类型的属性，表示令牌
    private String token;

    // 定义一个User类型的属性，表示用户信息
    private User user;

    // 定义一个带有两个参数的构造方法，用于创建LoginInfo对象
    public LoginInfo(String token, User user) {
        this.token = token;
        this.user = user;
    }

    // 重写toString方法，使用ToStringBuilder来生成字符串表示
    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
                .append("token", token)
                .append("user", user)
                .toString();
    }
}
