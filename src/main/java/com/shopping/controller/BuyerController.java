package com.shopping.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/*
*@author: pele
*@time: 2017/12/4 16:50
*@project: ShoppingSystem
*@description:买家controller
*/
@Controller
@RequestMapping("buyer")
public class BuyerController {

    @RequestMapping("test")
    public String test(){
        return "/buyer/test";
    }
}
