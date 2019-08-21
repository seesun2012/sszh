package com.sszh.server.sso.mapper;

import com.sszh.core.mapper.IBaseMapper;
import com.sszh.server.sso.api.entity.SysMenuEntity;
import com.sszh.server.sso.api.entity.SysUserEntity;
import tk.mybatis.mapper.common.BaseMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface SysUserMapper extends IBaseMapper<SysUserEntity> {

    SysUserEntity queryLogin(String account);
    
}