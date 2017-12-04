package com.shopping.service;

import com.shopping.common.pojo.AjaxResult;
import com.shopping.pojo.User;

/*
*@author: pele
*@time: 2017/12/4 13:16
*@project: ShoppingSystem
*@description:用户service接口
*/
public interface UserService {
    User getUserInfo(Integer id);
    AjaxResult addUser(String username,String password,String mobile,Byte type);
    AjaxResult login(User user);
}
