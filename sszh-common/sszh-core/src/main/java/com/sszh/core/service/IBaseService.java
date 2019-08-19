package com.sszh.core.service;

import java.util.List;

import com.sszh.core.entity.CommonEntity;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.apache.ibatis.session.RowBounds;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

/**
 * 持久层基础接口（传入参数必须是继承了CommonEntity实体）
 */
public interface IBaseService<T extends CommonEntity> {

    /**
     * 全属性插入
     *
     * @param t
     * @return
     */
    public int insert(T t);

    /**
     * 按给定属性插入,效率比较高
     *
     * @param t
     * @return
     */
    public int insertSelective(T t);

    /**
     * 批量插入
     *
     * @param t
     * @return
     */
    public int insertList(List<T> t);

    /**
     * 查询
     *
     * @param t
     * @return
     */
    public List<T> select(T t);

    /**
     * 查询一个
     *
     * @param t
     * @return
     */
    public T selectOne(T t);

    /**
     * 获取全部数据，通常和分页一起用
     *
     * @return
     */
    public List<T> selectAll();

    /**
     * 查询符合条件的数据总条数
     *
     * @param t
     * @return
     */
    public int selectCount(T t);

    /**
     * 根据模板查询条数
     *
     * @param example
     * @return
     */
    public int selectCountByExample(Example example);

    /**
     * 根据主键查询
     *
     * @param t
     * @return
     */
    public T selectByPrimaryKey(T t);

    /**
     * 根据模板查询
     *
     * @param example
     * @return
     */
    public List<T> selectByExample(Example example);

    /**
     * 根据实体查询数据
     *
     * @param t
     * @return
     */
    public T selectOneByExample(T t);

    /**
     * 根据行号查询
     *
     * @param t
     * @param rowBounds
     * @return
     */
    public List<T> selectByRowBounds(T t, RowBounds rowBounds);

    /**
     * 根据模板和行号查询
     *
     * @param example
     * @param rowBounds
     * @return
     */
    public List<T> selectByExampleAndRowBounds(Example example, RowBounds rowBounds);

    /**
     * 根据模板删除
     *
     * @param example
     * @return
     */
    public int deleteByExample(Example example);

    /**
     * 根据主键删除
     *
     * @param t
     * @return
     */
    public int deleteByPrimaryKey(T t);

    /**
     * 删除
     *
     * @param t
     * @return
     */
    public int delete(T t);

    /**
     * 根据模板更新，全字段更新
     *
     * @param example
     * @return
     */
    public int updateByExample(T t, Example example);

    /**
     * 根据模板更新选定子段
     *
     * @param t
     * @param example
     * @return
     */
    public int updateByExampleSelective(T t, Example example);

    /**
     * 根据主键更新,将设置的属性进行更新，如果不设置属性，自动更新为null。
     *
     * @param t
     * @return
     */
    public int updateByPrimaryKey(T t);

    /**
     * 根据主键更新,将设置的属性进行更新，没有设置的属性不更新。推荐
     *
     * @param t
     * @return
     */
    public int updateByPrimaryKeySelective(T t);

    
    List<T> selectPage(int pageNum, int pageSize);

    List<T> selectPage(int pageNum, int pageSize, T entity);
    
}
