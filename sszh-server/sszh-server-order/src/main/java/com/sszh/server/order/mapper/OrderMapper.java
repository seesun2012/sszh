package com.sszh.server.order.mapper;


import com.sszh.server.order.api.entity.OrderBean;

public interface OrderMapper {

    OrderBean selectByPrimaryKey(String id);

    int insertSelective(OrderBean record);

}