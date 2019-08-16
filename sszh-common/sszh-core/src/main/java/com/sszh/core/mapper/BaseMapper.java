package com.sszh.core.mapper;

import com.sszh.core.entity.CommonEntity;
import tk.mybatis.mapper.common.ConditionMapper;
import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

/**
 * 通用 Mapper, 如果被扫描到会报异常
 */
public interface BaseMapper<T extends CommonEntity> extends tk.mybatis.mapper.common.BaseMapper<T>, ConditionMapper<T>, IdsMapper<T>, InsertListMapper<T> {


}