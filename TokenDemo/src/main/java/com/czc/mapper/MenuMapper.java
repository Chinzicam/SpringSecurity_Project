package com.czc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.czc.entity.Menu;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author : chinzicam
 * @create 2023/8/31 10:24
 */
//BaseMapper是mybatisplus官方提供的接口，里面提供了很多单表查询的方法
@Service
public interface MenuMapper extends BaseMapper<Menu> {
    List<String> selectPermsByUserId(Long id);
}