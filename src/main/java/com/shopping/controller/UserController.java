package com.shopping.controller;

import com.shopping.pojo.User;
import com.shopping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/*
*@author: pele
*@time: 2017/12/4 13:19
*@project: ShoppingSystem
*@description:用户相关controller
*/
@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("getInfo")
    @ResponseBody
    public User getUserInfoById(Integer id){
        User user = userService.getUserInfo(id);
        return user;
    }
}
