package com.shopping.controller;

import com.shopping.pojo.Item;
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
}
