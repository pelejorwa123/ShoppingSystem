package com.shopping.service;

import com.shopping.common.pojo.AjaxResult;
import com.shopping.pojo.Item;
import com.shopping.pojo.Order;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    AjaxResult uploadPic(MultipartFile pic) throws IOException;
    void addItem(Item item);
    List<Order> getOrdersByItemName(String itemName,Integer storeId);
}
