package com.shopping.pojo;

/*
*@author: pele
*@time: 2017/12/5 9:57
*@project: ShoppingSystem
*@description:商品查询包装类，新增店铺名字段
*/
public class ItemQuery extends Item{
    private String storeName;

    public ItemQuery(Item item){
        setId(item.getId());
        setName(item.getName());
        setStoreId(item.getStoreId());
        setImgUrl(item.getImgUrl());
        setDescription(item.getDescription());
        setPrice(item.getPrice());
        setNum(item.getNum());
        setStatus(item.getStatus());
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }
}
