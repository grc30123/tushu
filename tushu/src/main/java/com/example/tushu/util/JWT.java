package com.example.tushu.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class JWT {

    public static String generateJWT(String name) {
        String token = Jwts.builder()
                .setSubject(name)//账户名
                .setExpiration(new Date(System.currentTimeMillis() + 600 * 600 * 1000))//最小单位为毫秒
                .signWith(SignatureAlgorithm.HS512, "secret")
                .compact();
        return token;
    }

    public static Object format(String input) {
        Instant instant = Instant.parse(input);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneId.of("UTC"));
        return formatter.format(instant);
    }
}
