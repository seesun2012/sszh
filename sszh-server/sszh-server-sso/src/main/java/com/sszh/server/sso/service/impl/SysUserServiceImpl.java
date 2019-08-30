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
    public IBaseMapper<SysUserEntity> getBaseMapper() {
        return sysUserMapper;
    }
    
    @Override
    public List<SysUserEntity> selectSelective(SysUserEntity record) {
        return sysUserMapper.selectSelective(record);
    }
    
    @Override
    public SysUserEntity loginQuery(String account) {
        return sysUserMapper.queryLogin(account);
    }

}