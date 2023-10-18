package com.example.tushu.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JWT {

    public static String generateJWT(String name) {
        String token = Jwts.builder()
                .setSubject(name)//账户名
                .setExpiration(new Date(System.currentTimeMillis() + 600 * 60 * 1000))//最小单位为毫秒
                .signWith(SignatureAlgorithm.HS512, "secret")
                .compact();
        return token;
    }
}
