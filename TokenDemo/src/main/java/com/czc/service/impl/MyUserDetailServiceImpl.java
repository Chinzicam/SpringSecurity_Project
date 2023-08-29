package com.czc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.czc.common.LoginUser;
import com.czc.entity.User;
import com.czc.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 重写登录认证
 * @author czc
 */
@Service
public class MyUserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //查询用户
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUserName,username);
        User user = userMapper.selectOne(wrapper);
        //没有查询到用户就抛出异常
        if(user==null){
            throw new RuntimeException("用户名或者密码错误");
        }
        //把查询到的user结果，封装成UserDetails类型，然后返回。
        //由于UserDetails是个接口，需要新建LoginUser类，作为UserDetails的实现类
        return new LoginUser(user);
    }
}