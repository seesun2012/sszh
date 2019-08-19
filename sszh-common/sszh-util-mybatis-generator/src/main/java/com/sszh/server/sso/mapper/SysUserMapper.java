package com.sszh.server.sso.mapper;

import com.sszh.server.sso.api.entity.SysUserEntity;

public interface SysUserMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysUserEntity record);

    int insertSelective(SysUserEntity record);

    SysUserEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysUserEntity record);

    int updateByPrimaryKey(SysUserEntity record);
}