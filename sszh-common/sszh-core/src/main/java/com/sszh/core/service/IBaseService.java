package com.sszh.core.service;

import java.util.List;

import com.sszh.core.entity.CommonEntity;
import org.apache.ibatis.exceptions.TooManyResultsException;
import tk.mybatis.mapper.entity.Condition;

/**
 * 持久层基础接口（传入参数必须是继承了CommonEntity实体）
 */
public interface IBaseService<T extends CommonEntity> {

    /**
     * 插入数据
     * @param model
     */
    void save(T model);

    /**
     * 批量插入数据
     * @param models
     */
    void save(List<T> models);

    /**
     * 通过主鍵刪除
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 批量刪除 
     * @param ids eg：ids -> "1,2,3,4"
     */
    void deleteByIds(String ids);

    /**
     * 更新
     * @param model
     */
    void update(T model);

    /**
     * 通过ID查找
     * @param id
     * @return
     */
    T findById(Integer id);

    /**
     * 通过Model中某个成员变量名称（非数据表中column的名称）查找,value需符合unique约束
     * @param fieldName
     * @param value
     * @return
     * @throws TooManyResultsException
     */
    T findBy(String fieldName, Object value) throws TooManyResultsException;

    /**
     * 通过多个ID查找
     * @param ids eg：ids -> "1,2,3,4"
     * @return
     */
    List<T> findByIds(String ids);

    /**
     * 根据条件查找
     * @param condition
     * @return
     */
    List<T> findByCondition(Condition condition);

    /**
     * 获取所有
     * @return
     */
    List<T> findAll();
    
}
