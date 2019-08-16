package com.sszh.server.sso.mapper;

import com.sszh.server.sso.api.entity.SysMenuEntity;

public interface SysMenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysMenuEntity record);

    int insertSelective(SysMenuEntity record);

    SysMenuEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysMenuEntity record);

    int updateByPrimaryKey(SysMenuEntity record);
}