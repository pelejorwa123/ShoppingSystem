package com.shopping.service;

import com.shopping.common.pojo.AjaxResult;
import com.shopping.pojo.*;

import java.util.List;

/*
*@author: pele
*@time: 2017/12/4 19:32
*@project: ShoppingSystem
*@description:买家服务接口
*/
public interface BuyerService {
    List<OrderQuery> getAllOrderList(Integer buyerId);
    List<ItemQuery> getItemsByName(String itemName);
    List<ItemQuery> getAllItem();
    Item getItemById(Long itemId);
    List<OrderQuery> getOrdersByItemName(String itemName,Integer buyId);
    List<CartQuery> getCartList(Integer buyerId);
    AjaxResult addCart(Integer buyerId,Long itemId);
}
