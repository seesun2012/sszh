package com.sszh.server.sso.service;


import com.sszh.core.service.IBaseService;
import com.sszh.server.sso.api.entity.SysUserEntity;

/**
 * 用户主业务处理类
 */
public interface ISysUserService extends IBaseService<SysUserEntity> {

    //登陆查询
    SysUserEntity loginQuery(String account);
    
}