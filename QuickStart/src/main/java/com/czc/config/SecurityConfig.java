package com.czc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

/**
 * @Author : chinzicam
 * @create 2023/8/31 14:43
 */
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    //注入security官方提供的AuthenticationSuccessHandler接口
    private AuthenticationSuccessHandler successHandler;

    @Autowired
    //注入security官方提供的AuthenticationSuccessHandler接口
    private AuthenticationFailureHandler failureHandler;
    @Autowired
    //注入security官方提供的LogoutSuccessHandler接口
    private LogoutSuccessHandler logoutSuccessHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);
        //把刚刚在MySuccessHandler类里面的"自定义'登录成功的处理器'"，配置给security
        http.formLogin()
                //登录认证成功的处理器
                .successHandler(successHandler)
                //登录认证失败的处理器
                .failureHandler(failureHandler);
        //登出成功的处理器的配置
        http.logout()
                //登出成功的处理器
                .logoutSuccessHandler(logoutSuccessHandler);

        //其它默认的认证接口，例如业务接口的认证限制，要配，因为你重写自定义了之后，原有的配置都被覆盖，不写的话业务接口就没有security认证拦截的功能了
        http.authorizeRequests().anyRequest().authenticated();
    }
}
