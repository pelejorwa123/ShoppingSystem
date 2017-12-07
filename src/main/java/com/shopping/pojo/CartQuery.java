package com.shopping.pojo;

/*
*@author: pele
*@time: 2017/12/6 15:56
*@project: ShoppingSystem
*@description:购物车查询包装类，增加商品店铺信息
*/
public class CartQuery extends Cart{
    private Integer storeId;
    private String storeName;
    private String imgUrl;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgUrl() {

        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public CartQuery(Cart cart){
        setId(cart.getId());

        setBuyerId(cart.getBuyerId());
        setItemId(cart.getItemId());
        setItemName(cart.getItemName());
        setNum(cart.getNum());
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }
}
