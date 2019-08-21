package com.sszh.server.sso.service.impl;

import com.sszh.core.mapper.IBaseMapper;
import com.sszh.core.service.BaseServiceImpl;
import com.sszh.server.sso.api.entity.SysUserEntity;
import com.sszh.server.sso.mapper.SysUserMapper;
import com.sszh.server.sso.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 用户主业务处理类
 */
@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUserEntity> implements ISysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;


    @Override
    public SysUserEntity selectByPrimaryKey(String id) {
        return this.selectByPrimaryKey(id);
    }

    @Override
    public SysUserEntity loginQuery(String account) {
        Example param = new Example(SysUserEntity.class);
        param.getSelectColumns();
        List<SysUserEntity> list = this.selectByExample(param);
        if (null == list && list.size() <= 0) return null;
        SysUserEntity bean = sysUserMapper.queryLogin(account);
        return bean;
    }

    @Override
    public IBaseMapper<SysUserEntity> getBaseMapper() {
        return sysUserMapper;
    }

}