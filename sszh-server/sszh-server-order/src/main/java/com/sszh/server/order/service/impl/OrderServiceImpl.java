package com.sszh.server.order.service.impl;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.sszh.server.order.api.entity.OrderBean;
import com.sszh.server.order.mapper.OrderMapper;
import com.sszh.server.order.service.IOrderService;
import com.sszh.server.sso.api.entity.UserBean;
import com.sszh.server.sso.api.feign.interfaces.UserClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class OrderServiceImpl implements IOrderService {

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private UserClient userClient;

    @Override
    public OrderBean selectByPrimaryKey(String id) {
        return orderMapper.selectByPrimaryKey(id);
    }


    @LcnTransaction                     //分布式事务
    @Transactional                      //本地事务
    @Override
    public Integer insertSelective(OrderBean record) throws Exception {
        if (null == record) throw new RuntimeException("order对象不能为空");
        UserBean user = new UserBean();
        user.setId(1001L);
        user.setUserName("张三");
        int ui = userClient.insertSelective(user);
        int oi = orderMapper.insertSelective(record);
        throw new RuntimeException("分布式事务测试order");
//        return oi;
    }
}
