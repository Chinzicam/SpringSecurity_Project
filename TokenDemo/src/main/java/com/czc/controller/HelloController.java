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
    @PreAuthorize("hasAuthority('system:test:list')")//判断用户是否有test权限，返回布尔类型
    public String hello(){
        return "欢迎，开始你新的学习旅程吧";
    }

    @PostMapping("/testCors")
    public ResponseResult testCors(){
        return new ResponseResult(200,"测试testCors");
    }
}
