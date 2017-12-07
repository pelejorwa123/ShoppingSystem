package com.shopping.service.impl;

import com.shopping.common.pojo.AjaxResult;
import com.shopping.mapper.CartMapper;
import com.shopping.mapper.ItemMapper;
import com.shopping.mapper.OrderMapper;
import com.shopping.mapper.UserMapper;
import com.shopping.pojo.*;
import com.shopping.service.BuyerService;
import org.aspectj.weaver.ast.Or;
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

    @Autowired
    CartMapper cartMapper;

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
     *@time: 2017/12/7 16:07
     *@package: com.shopping.service.impl
     *@descroption:生成订单，插入数据库
     */
    @Override
    public AjaxResult addOrder(Order order) {
        orderMapper.insertSelective(order);
        return AjaxResult.ok();
    }

    /**
     *@author: pele
     *@time: 2017/12/7 15:57
     *@package: com.shopping.service.impl
     *@descroption:根据相关信息生成一个订单对象
     */
    @Override
    public OrderQuery makeOrder(Integer buyerId, Long itemId, Integer num) {
        Order order = new Order();
        order.setBuyerId(buyerId);
        order.setItemId(itemId);
        order.setNum(num);
        Item item = itemMapper.selectByPrimaryKey(itemId);
        order.setItemName(item.getName());
        order.setStoreId(item.getStoreId());
        Integer price = item.getPrice()*num;
        order.setTotalPrice(price);
        OrderQuery orderQuery = new OrderQuery(order);
        User store = userMapper.selectByPrimaryKey(item.getStoreId());
        orderQuery.setStoreName(store.getUsername());
        return orderQuery;
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
     *@time: 2017/12/5 23:59
     *@package: com.shopping.service.impl
     *@descroption:根据商品名字查询所有订单
     */
    @Override
    public List<OrderQuery> getOrdersByItemName(String itemName,Integer buyId) {
        Order orderExample = new Order();
        orderExample.setBuyerId(buyId);
        orderExample.setItemName(itemName);
        List<Order> orderList = orderMapper.selectOrdersByItemName(orderExample);
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
     *@time: 2017/12/6 16:50
     *@package: com.shopping.service.impl
     *@descroption:根据用户Id，商品Id更新购物车
     */
    @Override
    public AjaxResult addCart(Integer buyerId, Long itemId) {
        //先判断购物车之前已经存在这个用户这个商品
        CartExample cartExample = new CartExample();
        CartExample.Criteria criteria = cartExample.createCriteria();
        criteria.andBuyerIdEqualTo(buyerId);
        criteria.andItemIdEqualTo(itemId);
        List<Cart> cartQueryResult = cartMapper.selectByExample(cartExample);
        Cart cart = new Cart();
        //如果没有，则进行插入操作
        if(null == cartQueryResult || cartQueryResult.isEmpty()){
            cart.setBuyerId(buyerId);
            cart.setItemId(itemId);
            cart.setNum(1);//默认数量设为1
            Item item = itemMapper.selectByPrimaryKey(itemId);
            cart.setItemName(item.getName());
            cartMapper.insertSelective(cart);
        }else{
            //否则直接更新记录
            cart = cartQueryResult.get(0);
            cart.setNum(cart.getNum()+1);//对购物车中的数量加1
            cartMapper.updateByPrimaryKey(cart);
        }
        return AjaxResult.ok(cart);
    }

    /**
     *@author: pele
     *@time: 2017/12/7 14:50
     *@package: com.shopping.service.impl
     *@descroption:跟据用户id和商品id删除购物车
     */
    @Override
    public AjaxResult delCart(Integer buyerId, Long itemId) {
        CartExample cartExample = new CartExample();
        CartExample.Criteria criteria = cartExample.createCriteria();
        criteria.andBuyerIdEqualTo(buyerId);
        criteria.andItemIdEqualTo(itemId);
        cartMapper.deleteByExample(cartExample);
        return AjaxResult.ok();
    }

    /**
     *@author: pele
     *@time: 2017/12/7 15:05
     *@package: com.shopping.service.impl
     *@descroption:根据商品店铺名字找出所有符合条件的店铺的商品
     */
    @Override
    public List<ItemQuery> getItemsByStoreName(String storeName) {
        List<User> storeList = userMapper.getStoresByName(storeName);
        List<ItemQuery> itemQueryList = new ArrayList<>();
        if(null != storeList && !storeList.isEmpty()){
            List<Integer> storeIdList = new ArrayList<>();
            for (User store:storeList){
                storeIdList.add(store.getId());
            }
            ItemExample itemExample = new ItemExample();
            ItemExample.Criteria criteria = itemExample.createCriteria();
            criteria.andStoreIdIn(storeIdList);
            List<Item> itemList = itemMapper.selectByExample(itemExample);
            for(Item item:itemList){
                User store = userMapper.selectByPrimaryKey(item.getStoreId());
                ItemQuery itemQuery = new ItemQuery(item);
                itemQuery.setStoreName(store.getUsername());
                itemQueryList.add(itemQuery);
            }
        }
        return itemQueryList;
    }

    /**
     *@author: pele
     *@time: 2017/12/6 15:58
     *@package: com.shopping.service.impl
     *@descroption:根据用户id查询出用户购物车的商品
     */
    @Override
    public List<CartQuery> getCartList(Integer buyerId) {
        CartExample cartExample = new CartExample();
        CartExample.Criteria criteria = cartExample.createCriteria();
        criteria.andBuyerIdEqualTo(buyerId);
        List<Cart> cartList = cartMapper.selectByExample(cartExample);
        //生成cartQuery
        List<CartQuery> cartQueryList = new ArrayList<>();
        for (Cart cart :cartList) {
            CartQuery cartQuery = new CartQuery(cart);
            Item item = itemMapper.selectByPrimaryKey(cart.getItemId());
            cartQuery.setStoreId(item.getStoreId());
            cartQuery.setImgUrl(item.getImgUrl());
            cartQuery.setDescription(item.getDescription());
            User store = userMapper.selectByPrimaryKey(item.getStoreId());
            cartQuery.setStoreName(store.getUsername());
            cartQueryList.add(cartQuery);
        }
        return cartQueryList;
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


    @Override
    public Item getItemById(Long itemId) {
        Item item = itemMapper.selectByPrimaryKey(itemId);
        return item;
    }
}
