package com.sszh.server.order.service;


import com.sszh.server.order.api.entity.OrderBean;

public interface IOrderService {

    //根据用户ID查询用户信息
    OrderBean selectByPrimaryKey(String id);

    //根据用户对象新增用户信息
    Integer insertSelective(OrderBean record) throws Exception;
    
}
