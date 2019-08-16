package com.sszh.server.sso.service;


import com.sszh.server.sso.api.entity.SysUserEntity;

/**
 * 用户主业务处理类
 */
public interface ISysUserService {

    //根据用户ID查询用户信息
    SysUserEntity selectByPrimaryKey(Long id);

    //根据用户对象新增用户信息
    Integer insertSelective(SysUserEntity record);

    //登陆查询
    SysUserEntity loginQuery(String account);
    
}