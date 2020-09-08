package org.linlinjava.litemall.db.common.service;

import java.util.List;

import org.linlinjava.litemall.db.util.Page;

public interface ICommonService {

	/**
     * 保存实体
     * @param entity
     */
    public void save(Object entity) throws Exception;
    
    /**
     * 根据主键获取对象
     * @param <T>
     * @param clazz 实体类
     * @param id    主键
     * @return
     */
    public <T> T getById(Object id)throws Exception;
    
    /**
     * 删除实体
     * @param entity
     */
	public void delete(Object entity)throws Exception;
	
    /**
     * 更新实体
     * @param entity
     */
	public void update(Object entity)throws Exception;
	
	/**
	 * 分页
	 * @param entity
	 * @param start
	 * @param length
	 * @return
	 * @throws Exception
	 */
	public Page getPageObjectsByHql(Object entity,int start,int length) throws Exception;
	
	/**
	 * 分页
	 * @param sql
	 * * @param countSql
	 * @param start
	 * @param length
	 * @return
	 * @throws Exception
	 */
	public Page getPage(String sql,String countsql, List<Object> params, int start, int length) throws Exception;
}
