package com.czc;

import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static com.czc.utils.JwtUtil.createJWT;
import static com.czc.utils.JwtUtil.parseJWT;

/**
 * jwt工具类测试
 */
@SpringBootTest
public class JwtTest {
    @Test
    public void CreateJwtTest() throws Exception {
        //加密指定字符串，xxjwt是123456加密后的密文
        String xxjwt = createJWT("czc123");
        System.out.println(xxjwt);
    }

    @Test
    public void ParseJwtTest() throws Exception {
        //解密字符串
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJjNWEwN2QxZWZiNjU0Y2YxOTAxODhiMWIxMTQzZWZkYiIsInN1YiI6ImN6YzEyMyIsImlzcyI6ImN6YyIsImlhdCI6MTY5MzM2MzU1MSwiZXhwIjoxNjkzMzY3MTUxfQ.S5ojH36WNuUhwX9VUeVNIORiIY7eYvloj5lGljoZ0oc";
        Claims claims = parseJWT(token);
        System.out.println(claims);
    }
}
