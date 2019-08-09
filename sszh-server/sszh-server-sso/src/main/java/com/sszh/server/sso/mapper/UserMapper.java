package com.sszh.server.sso.mapper;

import com.sszh.server.sso.entity.UserBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

@Repository
@MapperScan
public interface UserMapper{

    UserBean selectByPrimaryKey(Long id);

    int insertSelective(UserBean record);

}