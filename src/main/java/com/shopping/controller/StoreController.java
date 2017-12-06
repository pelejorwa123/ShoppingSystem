package com.shopping.controller;

import com.shopping.pojo.Item;
import com.shopping.pojo.Order;
import com.shopping.pojo.User;
import com.shopping.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/*
*@author: pele
*@time: 2017/12/5 11:13
*@project: ShoppingSystem
*@description:买家服务
*/
@Controller
@RequestMapping("store")
public class StoreController {

    @Autowired
    StoreService storeService;

    /**
     *@author: pele
     *@time: 2017/12/5 11:13
     *@package: com.shopping.controller
     *@descroption:查询出所有卖家店铺的商品
     */
    @RequestMapping("itemlist")
    public String getAllItems(HttpSession httpSession, Model model){
        User store = (User) httpSession.getAttribute("UserInfo");
        Integer storeId = store.getId();
        List<Item> itemList = storeService.getAllItemByStoreId(storeId);
        model.addAttribute("items",itemList);
        return "/store/items";
    }

    /**
     *@author: pele
     *@time: 2017/12/5 11:23
     *@package: com.shopping.controller
     *@descroption:根据商品名搜索店铺全部商品
     */
    @RequestMapping("search")
    public String getItemsByName(String itemName,HttpSession httpSession,Model model){
        User store = (User) httpSession.getAttribute("UserInfo");
        Integer storeId = store.getId();
        List<Item> itemList = storeService.getItemByName(itemName,storeId);
        model.addAttribute("itemName",itemName);
        model.addAttribute("items",itemList);
        return "/store/items";
    }

    /**
     *@author: pele
     *@time: 2017/12/5 11:59
     *@package: com.shopping.controller
     *@descroption:查询该店铺的全部订单
     */
    @RequestMapping("orderlist")
    public String getOrderList(HttpSession httpSession,Model model){
        User store = (User) httpSession.getAttribute("UserInfo");
        Integer storeId = store.getId();
        List<Order> orderList = storeService.getOrderListByStoreId(storeId);
        model.addAttribute("orders",orderList);
        return "/store/orders";
    }

    /**
     *@author: pele
     *@time: 2017/12/5 12:19
     *@package: com.shopping.controller
     *@descroption:根据商品ID修改商品，跳转到修改商品的页面
     */
    @RequestMapping("toUpdateItem")
    public String toUpdateItem(Long itemId,Model model){
        Item item = storeService.getItemByItemId(itemId);
        model.addAttribute("item",item);
        return "/store/editItem";
    }

    /**
     *@author: pele
     *@time: 2017/12/5 12:45
     *@package: com.shopping.controller
     *@descroption:更新商品
     */
    @RequestMapping("updateItem")
    public String updateItem(Item item){
        storeService.updateItem(item);
        return "redirect:/store/itemlist";
    }
}
