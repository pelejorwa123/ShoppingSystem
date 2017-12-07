package com.shopping.controller;

import com.shopping.common.pojo.AjaxResult;
import com.shopping.pojo.Item;
import com.shopping.pojo.Order;
import com.shopping.pojo.User;
import com.shopping.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
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

    /**
     *@author: pele
     *@time: 2017/12/6 17:24
     *@package: com.shopping.controller
     *@descroption:展示添加商品页面
     */
    @RequestMapping("toAddItem")
    public String toAddItem(){
        return "/store/addItem";
    }

    /**
     *@author: pele
     *@time: 2017/12/6 17:47
     *@package: com.shopping.controller
     *@descroption:上传图片
     */
    @RequestMapping("uploadPic")
    @ResponseBody
    public AjaxResult uploadPic(MultipartFile pic) throws IOException {
        AjaxResult result = storeService.uploadPic(pic);
        return result;
    }

    /**
     *@author: pele
     *@time: 2017/12/6 22:00
     *@package: com.shopping.controller
     *@descroption:图片上传
     */
    @RequestMapping("addItem")
    public String addItem(Item item,HttpSession httpSession){
        User user = (User) httpSession.getAttribute("UserInfo");
        item.setStoreId(user.getId());
        storeService.addItem(item);
        return "redirect:/store/itemlist";
    }

    /**
     *@author: pele
     *@time: 2017/12/6 23:48
     *@package: com.shopping.controller
     *@descroption:订单内搜索指定商品名字的订单
     */
    @RequestMapping("order/search")
    public String getOrderByItemName(HttpSession httpSession,Model model,String name){
        User user = (User) httpSession.getAttribute("UserInfo");
        List<Order> orderList = storeService.getOrdersByItemName(name,user.getId());
        model.addAttribute("orders",orderList);
        model.addAttribute("name",name);
        return "/store/orders";
    }
}
