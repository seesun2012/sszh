package com.sszh.server.sso.service.impl;

import com.sszh.server.sso.entity.UserBean;
import com.sszh.server.sso.mapper.UserMapper;
import com.sszh.server.sso.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public Integer insertSelective(UserBean record) {
        return userMapper.insertSelective(record);
    }

}