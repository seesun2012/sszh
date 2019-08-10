package com.sszh.server.sso.service.impl;

import com.codingapi.txlcn.tc.annotation.DTXPropagation;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.codingapi.txlcn.tc.annotation.TxTransaction;
import com.codingapi.txlcn.tc.annotation.TxcTransaction;
import com.sszh.server.sso.api.entity.UserBean;
import com.sszh.server.sso.mapper.UserMapper;
import com.sszh.server.sso.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户主业务处理类
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserBean selectByPrimaryKey(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @LcnTransaction                     //分布式事务
    @Transactional                      //本地事务
    @Override
    public Integer insertSelective(UserBean record) {
        if (null == record) throw new RuntimeException("user对象不能为空");
        Integer i = userMapper.insertSelective(record);
        System.out.println("-------------" + i);
        //        throw new RuntimeException("测试事务回滚");
        return i;
    }

}