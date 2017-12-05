package com.shopping.service.impl;

import com.shopping.mapper.ItemMapper;
import com.shopping.mapper.OrderMapper;
import com.shopping.mapper.UserMapper;
import com.shopping.pojo.*;
import com.shopping.service.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/*
*@author: pele
*@time: 2017/12/4 19:33
*@project: ShoppingSystem
*@description:买家服务实现类
*/
@Service
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    ItemMapper itemMapper;

    /**
     *@author: pele
     *@time: 2017/12/4 19:34
     *@package: com.shopping.service.impl
     *@descroption:根据买家ID
     */
    @Override
    public List<OrderQuery> getAllOrderList(Integer buyerId) {
/*        OrderExample orderExample = new OrderExample();
        OrderExample.Criteria criteria = orderExample.createCriteria();
        criteria.andBuyerIdEqualTo(id);
        List<Order> orderList = orderMapper.selectByExample(orderExample);
        List<OrderQuery> orderQueryList = new ArrayList<>();
        for(Order order:orderList){
            //查询所有订单的卖家名
            User store = userMapper.selectByPrimaryKey(order.getStoreId());
            OrderQuery orderQuery = new OrderQuery(order);
            orderQueryList.add(orderQuery);
        }
        return orderQueryList*/;
        List<Order> orderList = orderMapper.selectOrdersByBuyerId(buyerId);
        List<OrderQuery> orderQueryList = new ArrayList<>();
        for(Order order:orderList){
            //查询所有订单的卖家名
            User store = userMapper.selectByPrimaryKey(order.getStoreId());
            OrderQuery orderQuery = new OrderQuery(order);
            orderQuery.setStoreName(store.getUsername());
            orderQueryList.add(orderQuery);
        }
        return orderQueryList;
    }

    /**
     *@author: pele
     *@time: 2017/12/5 9:58
     *@package: com.shopping.service.impl
     *@descroption:根据名字搜索全部商品，返回商品包装类列表
     */
    @Override
    public List<ItemQuery> getItemsByName(String itemName) {
        List<Item> itemList = itemMapper.selectItemsByName(itemName);
        //转换成商品包装类，加入店铺名字
        List<ItemQuery> itemQueryList = new ArrayList<>();
        for(Item item:itemList){
            User store = userMapper.selectByPrimaryKey(item.getStoreId());
            ItemQuery itemQuery = new ItemQuery(item);
            itemQuery.setStoreName(store.getUsername());
            itemQueryList.add(itemQuery);
        }
        return itemQueryList;
    }

    /**
     *@author: pele
     *@time: 2017/12/5 10:56
     *@package: com.shopping.service.impl
     *@descroption:列出全部商品
     */
    @Override
    public List<ItemQuery> getAllItem() {
        List<Item> itemList = itemMapper.selectByExample(new ItemExample());
        //转换成商品包装类，加入店铺名字
        List<ItemQuery> itemQueryList = new ArrayList<>();
        for(Item item:itemList){
            User store = userMapper.selectByPrimaryKey(item.getStoreId());
            ItemQuery itemQuery = new ItemQuery(item);
            itemQuery.setStoreName(store.getUsername());
            itemQueryList.add(itemQuery);
        }
        return itemQueryList;
    }
}
