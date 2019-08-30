package com.sszh.server.sso.mapper;

import com.sszh.core.mapper.IBaseMapper;
import com.sszh.server.sso.api.entity.SysUserEntity;

import java.util.List;

public interface SysUserMapper extends IBaseMapper<SysUserEntity> {

    List<SysUserEntity> selectSelective(SysUserEntity record);

    SysUserEntity queryLogin(String account);
    
}