package com.shopping.service;

import com.shopping.pojo.Item;
import com.shopping.pojo.ItemQuery;
import com.shopping.pojo.Order;
import com.shopping.pojo.OrderQuery;

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
}
