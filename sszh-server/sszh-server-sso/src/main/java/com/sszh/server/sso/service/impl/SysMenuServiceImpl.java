package com.sszh.server.sso.service.impl;

import com.sszh.core.mapper.IBaseMapper;
import com.sszh.core.service.BaseServiceImpl;
import com.sszh.server.sso.api.entity.SysMenuEntity;
import com.sszh.server.sso.api.entity.SysUserEntity;
import com.sszh.server.sso.mapper.SysMenuMapper;
import com.sszh.server.sso.mapper.SysUserMapper;
import com.sszh.server.sso.service.ISysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 系统菜单主业务处理类
 */
@Service
public class SysMenuServiceImpl extends BaseServiceImpl<SysMenuEntity> implements ISysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;
    
    @Override
    public IBaseMapper<SysMenuEntity> getBaseMapper() {
        return sysMenuMapper;
    }
    
}