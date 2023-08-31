package com.czc.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author czc
 */
@RestController
@RequestMapping("/hello")
public class HelloController {
    @RequestMapping()
    @PreAuthorize("hasAuthority('test')")//判断用户是否有test权限，返回布尔类型
    public String hello(){
        return "欢迎，开始你新的学习旅程吧";
    }
}
