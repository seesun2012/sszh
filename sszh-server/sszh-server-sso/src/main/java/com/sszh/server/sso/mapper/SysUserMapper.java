package com.sszh.server.sso.mapper;

import com.sszh.core.mapper.IBaseMapper;
import com.sszh.server.sso.api.entity.SysUserEntity;
import org.apache.ibatis.annotations.Select;
import org.springframework.context.annotation.Primary;

@Primary
public interface SysUserMapper extends IBaseMapper<SysUserEntity> {

    SysUserEntity queryLogin(String account);
    
}