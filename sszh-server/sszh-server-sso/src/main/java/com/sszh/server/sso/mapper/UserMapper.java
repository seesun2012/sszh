package com.sszh.server.sso.mapper;

import com.sszh.server.sso.api.entity.UserBean;

public interface UserMapper{

    UserBean selectByPrimaryKey(Long id);

    int insertSelective(UserBean record);

}