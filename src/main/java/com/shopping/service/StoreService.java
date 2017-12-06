package com.shopping.service;

import com.shopping.pojo.Item;
import com.shopping.pojo.Order;

import java.util.List;

/*
*@author: pele
*@time: 2017/12/5 11:15
*@project: ShoppingSystem
*@description:
*/
public interface StoreService {
    List<Item> getAllItemByStoreId(Integer storeId);
    List<Item> getItemByName(String itemName,Integer storeId);
    List<Order> getOrderListByStoreId(Integer storeId);
    Item getItemByItemId(Long itemId);
    void updateItem(Item item);
}
