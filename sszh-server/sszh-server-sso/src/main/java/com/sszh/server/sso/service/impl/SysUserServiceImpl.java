package com.sszh.server.sso.service.impl;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.sszh.server.sso.api.entity.SysUserEntity;
import com.sszh.server.sso.mapper.SysUserMapper;
import com.sszh.server.sso.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户主业务处理类
 */
@Service
public class SysUserServiceImpl implements ISysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public SysUserEntity selectByPrimaryKey(Long id) {
        return sysUserMapper.selectByPrimaryKey(id);
    }

    @LcnTransaction                     //分布式事务
    @Transactional                      //本地事务
    @Override
    public Integer insertSelective(SysUserEntity record) {
        if (null == record) throw new RuntimeException("user对象不能为空");
        Integer i = sysUserMapper.insertSelective(record);
        System.out.println("-------------" + i);
        //        throw new RuntimeException("测试事务回滚");
        return i;
    }

    @Override
    public SysUserEntity loginQuery(String account) {
        return sysUserMapper.loginQuery(account);
    }

}