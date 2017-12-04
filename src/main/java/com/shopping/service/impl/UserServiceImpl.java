package com.shopping.service.impl;

import com.shopping.mapper.UserMapper;
import com.shopping.pojo.User;
import com.shopping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
*@author: pele
*@time: 2017/12/4 13:17
*@project: ShoppingSystem
*@description:用户service实现类
*/
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserMapper userMapper;


    @Override
    public User getUserInfo(Integer id) {
        User user = userMapper.selectByPrimaryKey(id);
        return user;
    }
}
