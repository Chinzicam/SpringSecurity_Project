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
        //加密指定字符串
        String xxjwt = createJWT("czc123");
        System.out.println(xxjwt);
    }

    @Test
    public void ParseJwtTest() throws Exception {
        //解密字符串
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIyYmNiMTU2NDAyMjA0NTlhODhhZmI3NDliZDYzODVkNSIsInN1YiI6ImN6YzEyMyIsImlzcyI6ImN6YyIsImlhdCI6MTY5MzM5NDY1NywiZXhwIjoxNjkzMzk4MjU3fQ.rqP94vTgHV0rU35oC0aiKfkjnaL21ifWroSjNNYxEYo";
        Claims claims = parseJWT(token);
        System.out.println(claims);
        //  {jti=2bcb15640220459a88afb749bd6385d5, sub=czc123, iss=czc, iat=1693394657, exp=1693398257}
    }
}
