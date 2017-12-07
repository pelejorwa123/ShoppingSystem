package com.shopping.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*
*@author: pele
*@time: 2017/12/5 22:27
*@project: ShoppingSystem
*@description:
*/
@Controller
public class PageController {

    @RequestMapping("buyer/header")
    public String showHeader(){
        return "/buyer/header";
    }

    @RequestMapping("buyer/footer")
    public String showBuyerFooter(){
        return "/buyer/footer";
    }

    @RequestMapping("store/footer")
    public String showStoreFooter(){
        return "/buyer/footer";
    }

    @RequestMapping("user/toSign")
    public String showSignPage(){
        return "/user/sign";
    }

}
