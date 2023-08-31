package com.czc.expression;

import com.czc.common.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 自定义权限校验的方法
 * @Author : chinzicam
 * @create 2023/8/31 14:31
 */
@Component()
public class czcExpressionRoot {

    //自定义权限校验的方法
    public boolean czcHasAuthority(String authority){

        //获取用户具有的权限字符串，有可能用户具有多个权限字符串，所以获取后是一个集合
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //LoginUser是我们在domain目录写好的实体类
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        List<String> permissions = loginUser.getPermissions();

        //判断用户权限集合中，是否存在跟业务接口(业务接口的权限字符串会作为authority形参传进来)一样的权限
        return permissions.contains(authority);
    }

}
