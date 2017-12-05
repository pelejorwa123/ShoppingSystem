package com.shopping.controller;

import com.shopping.pojo.*;
import com.shopping.service.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/*
*@author: pele
*@time: 2017/12/4 16:50
*@project: ShoppingSystem
*@description:买家controller
*/
@Controller
@RequestMapping("buyer")
public class BuyerController {

    @Autowired
    BuyerService buyerService;

    @RequestMapping("test")
    public String test(){
        return "/buyer/test";
    }

    /**
     *@author: pele
     *@time: 2017/12/4 19:39
     *@package: com.shopping.controller
     *@descroption:根据用户ID查询出所有订单
     */
    @RequestMapping("orderlist")
    public String getAllOrders(HttpServletRequest request, Model model){
        User user = (User) request.getSession().getAttribute("UserInfo");
        List<OrderQuery> orderList = buyerService.getAllOrderList(user.getId());
        model.addAttribute("orders",orderList);
        return "/buyer/orders";
    }

    /**
     *@author: pele
     *@time: 2017/12/5 9:48
     *@package: com.shopping.controller
     *@descroption:根据商品名搜索全部商品
     */
    @RequestMapping("search")
    public String getItemsByName(String itemName,Model model){
        List<ItemQuery> itemList = buyerService.getItemsByName(itemName);
        model.addAttribute("items",itemList);
        model.addAttribute("itemName",itemName);
        return "/buyer/items";
    }

    /**
     *@author: pele
     *@time: 2017/12/5 10:53
     *@package: com.shopping.controller
     *@descroption:列出所有商品列表
     */
    @RequestMapping("itemlist")
    public String items(Model model){
        List<ItemQuery> itemQueryList = buyerService.getAllItem();
        model.addAttribute("items",itemQueryList);
        return "/buyer/items";
    }
}
