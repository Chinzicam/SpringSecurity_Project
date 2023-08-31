package com.czc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author czc
 */
@RestController
@RequestMapping()
public class HelloController {

    @RequestMapping("/hello")
    public String hello(){
        return "欢迎，开始你的学习旅程吧";
    }

}
