package com.sszh.core.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sszh.core.entity.CommonEntity;
import com.sszh.core.mapper.IBaseMapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public abstract class BaseServiceImpl<T extends CommonEntity> implements IBaseService<T> {

    // 获取当前Mapper实例（因为spring与JDK8配合使用时，会将泛型当作两个实例注入到IOC，IOC无法识别到底以那个为主，启动就会报错）
    public abstract IBaseMapper<T> getBaseMapper();
    
    // 根据实体条件查询（每个Mapper.xml必须实现一个此方法，不然下面的方法会报错）
    public abstract List<T> selectSelective(T record);

    //    // 线程池
    //    //    protected static ExecutorService executorService = Executors.newFixedThreadPool(30);
    
    /** ======================================================== 新增 ======================================================== **/
    // 新增（默认）
    @Transactional
    @Override
    public int insert(T record) {
        return getBaseMapper().insert(record);
    }
    // 根据条件新增（效率比较高）
    @Transactional
    @Override
    public int insertSelective(T record) {
        return getBaseMapper().insertSelective(record);
    }
    // 新增（默认，返回自增主键）
    @Transactional
    @Override
    public int insertUseGeneratedKeys(T record) {
        return getBaseMapper().insertUseGeneratedKeys(record);
    }
    // 批量新增
    @Transactional
    @Override
    public int insertBatch(List<T> recordList) {
        return getBaseMapper().insertList(recordList);
    }
    
    /** ======================================================== 删除 ======================================================== **/
    // 根据ID删除
    @Transactional
    @Override
    public int deleteByPrimaryKey(Object id) {
        return getBaseMapper().deleteByPrimaryKey(id);
    }
    
    /** ======================================================== 修改 ======================================================== **/
    // 根据ID更新（如果不设置属性，自动更新为null）
    @Transactional
    @Override
    public int updateByPrimaryKey(T record) {
        return getBaseMapper().updateByPrimaryKey(record);
    }
    // 推荐：根据ID更新（将设置的属性进行更新，没有设置的属性不更新）
    @Transactional
    @Override
    public int updateByPrimaryKeySelective(T record) {
        return getBaseMapper().updateByPrimaryKeySelective(record);
    }
    
    /** ======================================================== 查询 ======================================================== **/
    // 根据ID查询
    @Override
    public T getById(Object id) {
        return getBaseMapper().selectByPrimaryKey(id);
    }
    @Override
    public T selectByPrimaryKey(Object id) {
        return getBaseMapper().selectByPrimaryKey(id);
    }
    // 查询实体（带条件）
    @Override
    public T getEntity(T record) {
        List<T> list = selectSelective(record);
        return list == null || list.size() <= 0 ? null : list.get(0);
    }
    // 获取全部数据
    @Override
    public List<T> getAll() {
        return selectSelective(null);
    }
    // 获取全部数据（带条件）
    @Override
    public List<T> getList(T record) {
        return selectSelective(record);
    }
    // 根据条件统计（带条件）
    @Override
    public long count(T record) {
        return PageHelper.count(() ->
            selectSelective(record)
        );
    }
    // 分页查询
    @Override
    public PageInfo<T> getPage(T record, int pageNum, int pageSize) {
        if (pageNum < 0) pageNum = 0;
        if (pageSize <= 0) pageNum = 10;
        PageInfo<T> page;
        if (null == record) {
            page = PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() ->
                selectSelective(null)
            );
            return page;
        }
        page = PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(
            () -> selectSelective(record)
        );
        return page;
    }

}