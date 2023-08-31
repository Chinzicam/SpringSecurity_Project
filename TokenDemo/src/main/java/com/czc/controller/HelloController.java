package com.czc.controller;


import com.czc.common.ResponseResult;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author czc
 */
@RestController
@RequestMapping()
public class HelloController {
    @RequestMapping("/hello")

    //hasAnyAuthority：判断当前用户是否具有指定的任意权限。示例：@PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")。

    //hasRole：判断当前用户是否具有指定的角色。示例：@PreAuthorize("hasRole('ROLE_ADMIN')")。

    //hasAnyRole：判断当前用户是否具有指定的任意角色。示例：@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")。

    //@PreAuthorize("hasAuthority('system:dept:list')")
    //@PreAuthorize("hasAnyAuthority('aaa','czc','system:dept:list')")
    //@PreAuthorize("hasRole('system:dept:list')")
    //@PreAuthorize("hasAnyRole('aaa','czc','system:dept:list')")

    //自定义权限校验的方法，czcHasAuthority
    @PreAuthorize("@czcExpressionRoot.czcHasAuthority('system:dept:list')")

    public String hello(){
        return "欢迎，开始你新的学习旅程吧";
    }

    /**
     * 基于配置的权限控制
     * @return
     */
    @RequestMapping("/configAuth")
    public ResponseResult configAuth(){
        return new ResponseResult(200,"访问成功");
    }


    /**
     * 跨域测试
     * @return
     */
    @PostMapping("/testCors")
    public ResponseResult testCors(){
        return new ResponseResult(200,"测试testCors");
    }
}
