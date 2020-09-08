package org.linlinjava.litemall.db.common.service.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.linlinjava.litemall.db.common.dao.CommonDao;
import org.linlinjava.litemall.db.common.service.ICommonService;
import org.linlinjava.litemall.db.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;


@Service("commonService")public abstract class CommonServiceImpl<T> implements ICommonService {
	private Class<T> clazz;
	
	@Autowired
	protected JdbcTemplate jdbcTemplate;
	
	@SuppressWarnings("unchecked")
	public CommonServiceImpl() {
		this.clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	public abstract CommonDao<T> getCommonDao();
	
    /**
     * 保存实体
     * @param entity
     */
    public void save(Object entity) throws Exception{
    	getCommonDao().save((T)entity);
    }
    
    /**
     * 根据主键获取对象
     * @param <T>
     * @param clazz 实体类
     * @param id    主键
     * @return
     */
    public <T> T getById(Object id)throws Exception{
    	return (T)getCommonDao().findById(id);
    }
    
    /**
     * 删除实体
     * @param entity
     */
	public void delete(Object entity) throws Exception{
		getCommonDao().delete((T)entity);
	}

	
    /**
     * 更新实体
     * @param entity
     */
	public void update(Object entity) throws Exception{
		getCommonDao().update((T)entity);
	}

	/**
	 * 获取分页对象
	 */
	public Page getPageObjectsByHql(Object entity,int start,int length) throws Exception{
		return getCommonDao().getPageObjectsByHql((T)entity,start,length);
	}
	
	/**
	 * 分页
	 * @param sql
	 * * @param countSql
	 * @param start
	 * @param length
	 * @return
	 * @throws Exception
	 */
	public Page getPage(String sql,String countsql, List<Object> params, int start, int length) throws Exception{
		return getCommonDao().getPage(sql, countsql, params, start, length);
	}
}