package com.czc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class PasswordEncoderTest {

    //如果不想在下面那行new的话，那么就在该类注入PasswordEncoder，例如如下
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void xxTestBCryptPasswordEncoder(){

//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        //模拟用户输入的密码
        String encode1 = passwordEncoder.encode("1234");
        //再模拟一次用户输入的密码
        String encode2 = passwordEncoder.encode("1234");
        //虽然这两次的密码都是一样的，但是加密后是不一样的。每次运行，对同一原文都会有不同的加密结果
        //原因:会添加随机的盐，加密结果=盐+原文+加密。达到每次加密后的密文都不相同的效果
        System.out.println(encode1);
        System.out.println(encode2);
    }
    @Test
    public void yyTestBCryptPasswordEncoder(){
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        //模拟用户输入了1234(第一个参数)，然后我们去跟数据库的密文进行比较(第二个参数)
        boolean result = passwordEncoder.matches("1234", "$2a$10$wVcK72jhwzgYhEzLlrMdQOwgmOQJcUywlce7ACbge6KYKqNL2lq36");

        //看一下比对结果
        System.out.println(result);

    }
}
