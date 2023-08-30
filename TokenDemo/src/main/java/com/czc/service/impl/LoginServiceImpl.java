package com.czc.service.impl;

import com.czc.common.LoginUser;
import com.czc.common.ResponseResult;
import com.czc.entity.User;
import com.czc.service.LoginService;
import com.czc.utils.JwtUtil;
import com.czc.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @Author : chinzicam
 * @create 2023/8/30 16:12
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisCache redisCache;

    @Override
    public ResponseResult login(User user) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        //如果用户认证不存在
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("登录失败");
        }
        //反之，存在,获取User对象
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        //利用userId生成jwt，存入redis，当作token
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);

        //authenticate存入redis
        redisCache.setCacheObject("login:"+userId,loginUser);
        //把token响应给前端
        HashMap<String,String> map = new HashMap<>();
        map.put("token",jwt);

        return new ResponseResult(200,"登陆成功",map);
    }
}
