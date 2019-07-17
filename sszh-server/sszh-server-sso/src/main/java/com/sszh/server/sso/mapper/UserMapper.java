package com.sszh.server.sso.mapper;

import com.sszh.server.sso.bean.User;

/**
 * 用户mapper
 */
public interface UserMapper {

    User selectByPrimaryKey(Long id);

}
