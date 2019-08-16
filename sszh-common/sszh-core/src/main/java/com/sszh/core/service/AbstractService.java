package com.sszh.core.service;

import com.sszh.core.entity.CommonEntity;
import com.sszh.core.mapper.BaseMapper;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Condition;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class AbstractService<T extends CommonEntity> {

    @Autowired
    protected BaseMapper<T> baseMapper;

    private Class<T> modelClass;    // 当前泛型真实类型的Class

    public AbstractService() {
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        modelClass = (Class<T>) pt.getActualTypeArguments()[0];
    }

    public void save(T model) {
        baseMapper.insertSelective(model);
    }

    public void save(List<T> models) {
        baseMapper.insertList(models);
    }

    public void deleteById(Integer id) {
        baseMapper.deleteByPrimaryKey(id);
    }

    public void deleteByIds(String ids) {
        baseMapper.deleteByIds(ids);
    }

    public void update(T model) {
        baseMapper.updateByPrimaryKeySelective(model);
    }

    public T findById(Integer id) {
        return baseMapper.selectByPrimaryKey(id);
    }

    @SuppressWarnings("unchecked")
    public T findBy(String fieldName, Object value) throws RuntimeException {
        try {
            T model = modelClass.newInstance();
            Field field = modelClass.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(model, value);
            return baseMapper.selectOne(model);
        } catch (Exception e) {
            throw new RuntimeException("查询操作异常!");
        }
    }

    public List<T> findByIds(String ids) {
        return baseMapper.selectByIds(ids);
    }

    public List<T> findByCondition(Condition condition) {
        return baseMapper.selectByCondition(condition);
    }

    public List<T> findAll() {
        return baseMapper.selectAll();
    }

}