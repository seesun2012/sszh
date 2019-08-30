package com.sszh.web.admin.mapper;


import com.sszh.core.mapper.IBaseMapper;
import com.sszh.web.admin.entity.SysMenuEntity;

import java.util.List;

public interface SysMenuMapper extends IBaseMapper<SysMenuEntity> {

    List<SysMenuEntity> selectSelective(SysMenuEntity record);
    
}