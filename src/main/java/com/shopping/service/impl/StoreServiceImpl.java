package com.shopping.service.impl;

import com.shopping.mapper.ItemMapper;
import com.shopping.mapper.OrderMapper;
import com.shopping.pojo.Item;
import com.shopping.pojo.ItemExample;
import com.shopping.pojo.Order;
import com.shopping.pojo.OrderExample;
import com.shopping.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

/*
*@author: pele
*@time: 2017/12/5 11:15
*@project: ShoppingSystem
*@description:
*/
@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    ItemMapper itemMapper;

    @Autowired
    OrderMapper orderMapper;

    /**
     *@author: pele
     *@time: 2017/12/5 11:16
     *@package: com.shopping.service.impl
     *@descroption:根据卖家id查询出所有卖家的商品
     */
    @Override
    public List<Item> getAllItemByStoreId(Integer storeId) {
        ItemExample itemExample = new ItemExample();
        ItemExample.Criteria criteria = itemExample.createCriteria();
        criteria.andStoreIdEqualTo(storeId);
        List<Item> itemList = itemMapper.selectByExample(itemExample);
        return itemList;
    }

    /**
     *@author: pele
     *@time: 2017/12/5 11:28
     *@package: com.shopping.service.impl
     *@descroption:根据店家ID以及商品名字模糊查询所有商品
     */
    @Override
    public List<Item> getItemByName(String itemName, Integer storeId) {
        List<Item> itemList = itemMapper.selectItemsByName(itemName);
        Iterator<Item> itemIterator = itemList.iterator();
        while (itemIterator.hasNext()){
            Item item = itemIterator.next();
            if(item.getStoreId()!=storeId){
                itemIterator.remove();
            }
        }
        return itemList;
    }

    /**
     *@author: pele
     *@time: 2017/12/5 12:02
     *@package: com.shopping.service.impl
     *@descroption:根据店铺ID查询出该店铺的全部订单
     */
    @Override
    public List<Order> getOrderListByStoreId(Integer storeId) {
        List<Order> orderList = orderMapper.selectOrdersByStoreId(storeId);
        return orderList;
    }

    /**
     *@author: pele
     *@time: 2017/12/5 12:22
     *@package: com.shopping.service.impl
     *@descroption:根据商品ID查询出商品
     */
    @Override
    public Item getItemByItemId(Long itemId) {
        Item item = itemMapper.selectByPrimaryKey(itemId);
        return item;
    }

    /**
     *@author: pele
     *@time: 2017/12/5 12:47
     *@package: com.shopping.service.impl
     *@descroption:更新商品
     */
    @Override
    public void updateItem(Item item) {
        itemMapper.updateByPrimaryKeySelective(item);
    }
}
