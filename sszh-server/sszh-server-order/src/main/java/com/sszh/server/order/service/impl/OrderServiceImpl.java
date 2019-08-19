package com.sszh.server.order.service.impl;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.sszh.common.util.string.StringUtils;
import com.sszh.server.order.api.entity.OrderBean;
import com.sszh.server.order.mapper.OrderMapper;
import com.sszh.server.order.service.IOrderService;
import com.sszh.server.sso.api.entity.SysUserEntity;
import com.sszh.server.sso.api.feign.interfaces.SysUserClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class OrderServiceImpl implements IOrderService {

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private SysUserClient sysUserClient;

    @Override
    public OrderBean selectByPrimaryKey(String id) {
        return orderMapper.selectByPrimaryKey(id);
    }


    @LcnTransaction                     //分布式事务
    @Transactional                      //本地事务
    @Override
    public Integer insertSelective(OrderBean record) throws Exception {
        if (null == record) throw new RuntimeException("order对象不能为空");
        SysUserEntity user = new SysUserEntity();
        user.setId(StringUtils.uuid19());
        user.setUserName("张三");
        int ui = sysUserClient.insertSelective(user);
        int oi = orderMapper.insertSelective(record);
        throw new RuntimeException("分布式事务测试order");
//        return oi;
    }
}
