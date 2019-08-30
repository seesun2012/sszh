package com.sszh.core.service;

import com.github.pagehelper.PageInfo;
import com.sszh.core.entity.CommonEntity;

import java.util.List;

/**
 * 持久层基础接口（传入参数必须是继承了CommonEntity实体）
 */
public interface IBaseService<T extends CommonEntity> {
    
    /** ======================================================== 新增 ======================================================== **/
    public int insert(T record);                                                       // 新增（默认）
    public int insertSelective(T record);                                              // 根据条件新增（效率比较高）
    public int insertUseGeneratedKeys(T record);                                       // 新增（默认，返回自增主键）
    public int insertBatch(List<T> recordList);                                        // 批量新增
    
    /** ======================================================== 删除 ======================================================== **/
    public int deleteByPrimaryKey(Object id);                                          // 根据ID删除
    
    /** ======================================================== 修改 ======================================================== **/
    public int updateByPrimaryKey(T record);                                           // 根据ID更新（如果不设置属性，自动更新为null）
    public int updateByPrimaryKeySelective(T record);                                  // 推荐：根据ID更新（将设置的属性进行更新，没有设置的属性不更新）
    
    /** ======================================================== 查询 ======================================================== **/
    public T getById(Object id);                                                       // 根据ID查询
    public T selectByPrimaryKey(Object id);                                            // 根据ID查询
    public T getEntity(T record);                                                      // 查询实体（带条件）
    public List<T> getAll();                                                           // 获取全部数据
    public List<T> getList(T record);                                                  // 获取全部数据（带条件）
    public long count(T record);                                                       // 根据条件统计（带条件）
    public PageInfo<T> getPage(T record, int pageNum, int pageSize);                   // 分页查询
    
}
