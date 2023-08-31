package com.czc.filter;

import com.czc.common.LoginUser;
import com.czc.utils.JwtUtil;
import com.czc.utils.RedisCache;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * jwt认证过滤器
 * @Author : chinzicam
 * @create 2023/8/30 16:50
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private RedisCache redisCache;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = request.getHeader("token");
        //如果请求没有携带token，那么就不需要解析token，不需要获取用户信息，直接放行就可以
        if(!StringUtils.hasText(token)){
            filterChain.doFilter(request, response);
            //return之后，就不会走下面那些代码
            return;
        }
        //解析token是否非法
        String userId;
        try {
            Claims claims = JwtUtil.parseJWT(token);
            userId=claims.getSubject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("token非法");
        }
        //从redis获取用户信息
        String redisKey="login:"+userId;
        LoginUser loginUser = redisCache.getCacheObject(redisKey);

        //判断获取到的用户信息是否为空，因为redis里面可能并不存在这个用户信息，例如缓存过期了
        if(Objects.isNull(loginUser)){
            throw new RuntimeException("用户未登录");
        }

        //将LoginUser用户信息，通过setAuthentication方法，存入SecurityContextHolder
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser,null,loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        //放行
        filterChain.doFilter(request,response);
    }
}
