package com.shopping.pojo;

/*
*@author: pele
*@time: 2017/12/4 19:48
*@project: ShoppingSystem
*@description:扩展order类，增加店铺名字字段
*/
public class OrderQuery extends Order{

    public OrderQuery(Order order){
        setId(order.getId());
        setBuyerId(order.getBuyerId());
        setStoreId(order.getStoreId());
        setItemId(order.getItemId());
        setItemName(order.getItemName());
        setNum(order.getNum());
        setAddress(order.getAddress());
        setCreateTime(order.getCreateTime());
        setMobile(order.getMobile());
        setTotalPrice(order.getTotalPrice());
    }

    private String storeName;

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }
}
