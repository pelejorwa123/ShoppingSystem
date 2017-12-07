package com.shopping.controller;

import com.shopping.common.pojo.AjaxResult;
import com.shopping.pojo.*;
import com.shopping.service.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
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
    public String getItemsByName(String name,Model model,Integer type,HttpSession httpSession){
        List<ItemQuery> itemList = new ArrayList<>();
        if(type == 1){
            itemList = buyerService.getItemsByName(name);
        }
        model.addAttribute("items",itemList);
        model.addAttribute("itemName",name);
        User user = (User) httpSession.getAttribute("UserInfo");
        model.addAttribute("user",user);
        return "/buyer/items";
    }

    /**
     *@author: pele
     *@time: 2017/12/5 10:53
     *@package: com.shopping.controller
     *@descroption:列出所有商品列表
     */
    @RequestMapping("itemlist")
    public String items(Model model, HttpSession httpSession){
        List<ItemQuery> itemQueryList = buyerService.getAllItem();
        User user = (User) httpSession.getAttribute("UserInfo");
        model.addAttribute("items",itemQueryList);
        model.addAttribute("user",user);
        return "/buyer/items";
    }

    /**
     *@author: pele
     *@time: 2017/12/5 13:01
     *@package: com.shopping.controller
     *@descroption:根据商品ID查询商品详情
     */
    @RequestMapping("getItemInfo")
    public String getItemInfo(Long itemId,String storeName,Model model){
        Item item = buyerService.getItemById(itemId);
        model.addAttribute("item",item);
        model.addAttribute("storeName",storeName);
        return "/buyer/itemInfo";
    }

    /**
     *@author: pele
     *@time: 2017/12/5 23:56
     *@package: com.shopping.controller
     *@descroption:根据商品名字搜索订单
     */
    @RequestMapping("order/search")
    public String getOrderByItemName(String name,Model model,HttpSession httpSession){
        User user = (User) httpSession.getAttribute("UserInfo");
        List<OrderQuery> orderQueryList = buyerService.getOrdersByItemName(name,user.getId());
        model.addAttribute("user",user);
        model.addAttribute("orders",orderQueryList);
        model.addAttribute("name",name);
        return "/buyer/orders";
    }

    /**
     *@author: pele
     *@time: 2017/12/6 15:51
     *@package: com.shopping.controller
     *@descroption:商品购物车展示页面
     */
    @RequestMapping("shopcart")
    public String getShopCart(HttpSession httpSession,Model model){
        User user = (User) httpSession.getAttribute("UserInfo");
        List<CartQuery> cartList = buyerService.getCartList(user.getId());
        model.addAttribute("user",user);
        model.addAttribute("carts",cartList);
        return "/buyer/shopcart";
    }

    /**
     *@author: pele
     *@time: 2017/12/6 16:45
     *@package: com.shopping.controller
     *@descroption:接受商品Id，加入购物车
     */
    @RequestMapping("addCart")
    @ResponseBody
    public AjaxResult addCart(HttpSession httpSession,Long itemId){
        User user = (User) httpSession.getAttribute("UserInfo");
        AjaxResult result = buyerService.addCart(user.getId(),itemId);
        return result;
    }
}
