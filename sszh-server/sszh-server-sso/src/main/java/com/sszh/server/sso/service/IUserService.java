package com.sszh.server.sso.service;


import com.sszh.server.sso.api.entity.UserBean;

/**
 * 用户主业务处理类
 */
public interface IUserService {

    //根据用户ID查询用户信息
    UserBean selectByPrimaryKey(Long id);

    //根据用户对象新增用户信息
    Integer insertSelective(UserBean record);

    //登陆查询
    UserBean loginQuery(String account);
    
}