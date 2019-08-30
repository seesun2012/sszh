package com.sszh.web.admin.service.impl;

import com.sszh.core.mapper.IBaseMapper;
import com.sszh.core.service.BaseServiceImpl;
import com.sszh.web.admin.entity.SysMenuEntity;
import com.sszh.web.admin.mapper.SysMenuMapper;
import com.sszh.web.admin.service.ISysMenuService;
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

    @Override
    public List<SysMenuEntity> selectSelective(SysMenuEntity record) {
        return sysMenuMapper.selectSelective(record);
    }

}