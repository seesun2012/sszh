package com.sszh.core.mapper;

import com.sszh.core.entity.CommonEntity;
import tk.mybatis.mapper.common.BaseMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * Mapper接口             基本的增、删、改、查方法
 * MySqlMapper接口        针对MySQL的额外补充接口，支持批量插入
 */
public interface IBaseMapper<T extends CommonEntity> extends Mapper<T>, BaseMapper<T>, MySqlMapper<T> {

    
    
}