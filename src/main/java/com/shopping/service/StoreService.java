package com.shopping.service;

import com.shopping.pojo.Item;

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
}
