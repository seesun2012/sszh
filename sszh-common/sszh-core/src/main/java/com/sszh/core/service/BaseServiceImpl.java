package com.sszh.core.service;

import com.github.pagehelper.PageHelper;
import com.sszh.core.entity.CommonEntity;
import com.sszh.core.mapper.IBaseMapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class BaseServiceImpl<T extends CommonEntity> implements IBaseService<T> {

    //取继承当前service的实例的mapper接口，如返回：UserMapper.java
    public abstract IBaseMapper<T> getBaseMapper();

    // 线程池
    protected static ExecutorService executorService = Executors.newFixedThreadPool(30);

    @Override
    public List<T> selectAll() {
        return this.getBaseMapper().selectAll();
    }

    @Override
    public T selectByPrimaryKey(T t) {
        return this.getBaseMapper().selectByPrimaryKey(t);
    }

    @Transactional                      //本地事务
    @Override
    public int deleteByPrimaryKey(T t) {
        return this.getBaseMapper().deleteByPrimaryKey(t);
    }

    @Transactional                      //本地事务
    @Override
    public int updateByPrimaryKey(T t) {
        return this.getBaseMapper().updateByPrimaryKey(t);
    }

    @Override
    public T selectOne(T t) {
        return this.getBaseMapper().selectOne(t);
    }

    @Override
    public List<T> select(T t) {
        return this.getBaseMapper().select(t);
    }

    @Override
    public int insert(T t) {
        return this.getBaseMapper().insert(t);
    }

    @Override
    public int insertList(List<T> t) {
        return this.getBaseMapper().insertList(t);
    }

    @Transactional                      //本地事务
    @Override
    public int delete(T t) {
        return this.getBaseMapper().delete(t);
    }

    @Override
    public int selectCount(T t) {
        return this.getBaseMapper().selectCount(t);
    }

    @Transactional                      //本地事务
    @Override
    public int insertSelective(T t) {
        return this.getBaseMapper().insertSelective(t);
    }

    @Override
    public int selectCountByExample(Example example) {
        return this.getBaseMapper().selectCountByExample(example);
    }

    @Override
    public List<T> selectByExample(Example example) {
        return this.getBaseMapper().selectByExample(example);
    }

    @Transactional                      //本地事务
    @Override
    public int deleteByExample(Example example) {
        return this.getBaseMapper().deleteByExample(example);
    }

    @Transactional                      //本地事务
    @Override
    public int updateByExample(T t, Example example) {
        return this.getBaseMapper().updateByExample(t, example);
    }

    @Transactional                      //本地事务
    @Override
    public int updateByPrimaryKeySelective(T t) {
        return this.getBaseMapper().updateByPrimaryKeySelective(t);
    }

    @Transactional                      //本地事务
    @Override
    public int updateByExampleSelective(T t, Example example) {
        return this.getBaseMapper().updateByExampleSelective(t, example);
    }

    @Override
    public T selectOneByExample(T t) {
        Example example = new Example(t.getClass());
        List<T> list = this.getBaseMapper().selectByExample(example);
        if (null == list || list.size() <= 0) return null;
        return list.get(0);
    }

    @Override
    public List<T> selectByRowBounds(T t, RowBounds rowBounds) {
        return this.getBaseMapper().selectByRowBounds(t, rowBounds);
    }

    @Override
    public List<T> selectByExampleAndRowBounds(Example example, RowBounds rowBounds) {
        return this.getBaseMapper().selectByExampleAndRowBounds(example, rowBounds);
    }
    
    @Override
    public List<T> selectPage(int pageNum, int pageSize) {
        if (pageNum < 0) pageNum = 0;
        if (pageSize <= 0) pageNum = 10;
        PageHelper.startPage(pageNum, pageSize);
        return this.getBaseMapper().selectAll();
    }

    @Override
    public List<T> selectPage(int pageNum, int pageSize, T entity) {
        if (pageNum < 0) pageNum = 0;
        if (pageSize <= 0) pageNum = 10;
        PageHelper.startPage(pageNum, pageSize);
        return this.getBaseMapper().select(entity);
    }

}