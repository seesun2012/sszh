package com.sszh.core.service;

import com.github.pagehelper.PageHelper;
import com.sszh.core.entity.CommonEntity;
import com.sszh.core.mapper.IBaseMapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public abstract class BaseServiceImpl<T extends CommonEntity> implements IBaseService<T> {

    @Resource
    private IBaseMapper<T> baseMapper;

    // 线程池
    protected static ExecutorService executorService = Executors.newFixedThreadPool(30);

    @Override
    public List<T> selectAll() {
        return baseMapper.selectAll();
    }

    @Override
    public T selectByPrimaryKey(T t) {
        return baseMapper.selectByPrimaryKey(t);
    }

    @Transactional                      //本地事务
    @Override
    public int deleteByPrimaryKey(T t) {
        return baseMapper.deleteByPrimaryKey(t);
    }

    @Transactional                      //本地事务
    @Override
    public int updateByPrimaryKey(T t) {
        return baseMapper.updateByPrimaryKey(t);
    }

    @Override
    public T selectOne(T t) {
        return baseMapper.selectOne(t);
    }

    @Override
    public List<T> select(T t) {
        return baseMapper.select(t);
    }

    @Override
    public int insert(T t) {
        return baseMapper.insert(t);
    }

    @Override
    public int insertList(List<T> t) {
        return baseMapper.insertList(t);
    }

    @Transactional                      //本地事务
    @Override
    public int delete(T t) {
        return baseMapper.delete(t);
    }

    @Override
    public int selectCount(T t) {
        return baseMapper.selectCount(t);
    }

    @Transactional                      //本地事务
    @Override
    public int insertSelective(T t) {
        return baseMapper.insertSelective(t);
    }

    @Override
    public int selectCountByExample(Example example) {
        return baseMapper.selectCountByExample(example);
    }

    @Override
    public List<T> selectByExample(Example example) {
        return baseMapper.selectByExample(example);
    }

    @Transactional                      //本地事务
    @Override
    public int deleteByExample(Example example) {
        return baseMapper.deleteByExample(example);
    }

    @Transactional                      //本地事务
    @Override
    public int updateByExample(T t, Example example) {
        return baseMapper.updateByExample(t, example);
    }

    @Transactional                      //本地事务
    @Override
    public int updateByPrimaryKeySelective(T t) {
        return baseMapper.updateByPrimaryKeySelective(t);
    }

    @Transactional                      //本地事务
    @Override
    public int updateByExampleSelective(T t, Example example) {
        return baseMapper.updateByExampleSelective(t, example);
    }

    @Override
    public T selectOneByExample(T t) {
        Example example = new Example(t.getClass());
        List<T> list = baseMapper.selectByExample(example);
        if (null == list || list.size() <= 0) return null;
        return list.get(0);
    }

    @Override
    public List<T> selectByRowBounds(T t, RowBounds rowBounds) {
        return baseMapper.selectByRowBounds(t, rowBounds);
    }

    @Override
    public List<T> selectByExampleAndRowBounds(Example example, RowBounds rowBounds) {
        return baseMapper.selectByExampleAndRowBounds(example, rowBounds);
    }
    
    @Override
    public List<T> selectPage(int pageNum, int pageSize) {
        if (pageNum < 0) pageNum = 0;
        if (pageSize <= 0) pageNum = 10;
        PageHelper.startPage(pageNum, pageSize);
        return baseMapper.selectAll();
    }

    @Override
    public List<T> selectPage(int pageNum, int pageSize, T entity) {
        if (pageNum < 0) pageNum = 0;
        if (pageSize <= 0) pageNum = 10;
        PageHelper.startPage(pageNum, pageSize);
        return baseMapper.select(entity);
    }

}