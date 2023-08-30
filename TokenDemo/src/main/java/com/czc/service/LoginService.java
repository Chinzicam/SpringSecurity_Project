package com.czc.service;

import com.czc.common.ResponseResult;
import com.czc.entity.User;

/**
 * @Author : chinzicam
 * @create 2023/8/30 16:11
 */
public interface LoginService {
    ResponseResult login(User user);

    ResponseResult logout();
}
