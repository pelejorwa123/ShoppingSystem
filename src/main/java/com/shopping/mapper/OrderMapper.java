package com.shopping.mapper;

import com.shopping.pojo.Order;
import com.shopping.pojo.OrderExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper {
    long countByExample(OrderExample example);

    int deleteByExample(OrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    List<Order> selectByExample(OrderExample example);

    Order selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByExample(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    List<Order> selectOrdersByBuyerId(Integer buyerId);

    List<Order> selectOrdersByStoreId(Integer storeId);

    List<Order> selectOrdersByItemName(Order order);

    List<Order> selectOrdersByItemNameAndStoreId(Order order);
}