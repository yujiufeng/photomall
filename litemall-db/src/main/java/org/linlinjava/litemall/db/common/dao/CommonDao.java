package org.linlinjava.litemall.db.common.dao;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.linlinjava.litemall.db.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public abstract class CommonDao<T>{
	
	@PersistenceContext
    private EntityManager entityManager;
	
	@Autowired
	protected JdbcTemplate jdbcTemplate;
	
	private Class<T> clazz;
	
	/**
	 * 用于Dao层子类使用的构造函数.
	 * 通过子类的泛型定义取得对象类型Class.
	 */
	@SuppressWarnings("unchecked")
	public CommonDao() {
		this.clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	/**
	 * 用于省略Dao层, 在Service层直接使用通用BaseDao的构造函数.
	 * 在构造函数中定义对象类型Class.
	 */
	public CommonDao(final Class<T> clazz) {
		this.clazz = clazz;
	}
	
	/**
	 * 保存新增的对象.
	 */
	@Transactional
	public void save(T entity) throws Exception {
		entityManager.persist(entity);
	}

	/**
	 * 更新对象
	 */
	@Transactional
	public void update(T entity) throws Exception {
		entityManager.merge(entity);
		entityManager.flush();
	}

	/**
	 * 删除对象.
	 * @param entity
	 * 对象必须是session中的对象或含id属性的transient对象.
	 */
	@Transactional
	public void delete(T entity) throws Exception {
		entityManager.remove(entity);
	}

	/**
	 * 按id获取对象.
	 */
	public T findById(Object id) throws Exception {
		return entityManager.find(clazz, id);
	}
	
	/**
	 * 按hql查询对象列表.
	 * @param values
	 *  数量可变的参数,按顺序绑定.
	 */
	public List<T> findByHql(String hql,List<Object> params)throws Exception {
		Query query=entityManager.createQuery(hql);
		int index = 1;
		for(Object o:params){
			query.setParameter(index,o);
			index++;
		}
        List<T> ret= query.getResultList();
		return ret;
	}

	/**
	 *获取分页对象
	 * @param start
	 * @param length
	 * @return
	 */
	public Page getPageObjectsByHql(T entity,int start,int length) {
		List<Object> params = getPageListParams(entity);
		Query query=entityManager.createQuery(getPageListHql(entity));
		query.setFirstResult(start);
		query.setMaxResults(length);
		int index = 1;
		for(Object o:params){
			query.setParameter(index,o);
			index++;
		}
		List<T> ret= query.getResultList();
		Page resultPage = new Page(ret, start, length,getCountByHql(getPageCountHql(entity),params));
		return resultPage;
	}
	
	/**
	 * 获取记录数
	 * @param hql
	 * @return
	 */
	public int getCountByHql(String hql,List<Object> params) {
		Query query=entityManager.createQuery(hql);
		int index = 1;
		for(Object o:params){
			query.setParameter(index,o);
			index++;
		}
		Object ret= query.getResultList().get(0);
		return ((Long)ret).intValue();
	}
	
	/**
	 * 组装查询分页中数据的总条数的hql
	 * @param entity
	 * @return
	 */
	protected abstract String getPageCountHql(T entity);
	

	/**
	 * 组装查询中数据的hql
	 * @param entity
	 * @return
	 */
	protected abstract String getPageListHql(T entity);
	
	/**
	 * 组装查询中数据的param
	 * @param entity
	 * @return
	 */
	protected abstract List<Object> getPageListParams(T entity);
	
	public Page getPage(String sql,String countsql, List<Object> params, int start, int length) throws Exception{
		String pageSql = "select * from ("+sql+") limit "+start+","+length;
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		Integer totalCount = 0;
		try {
			resultList = jdbcTemplate.queryForList(pageSql, params.toArray(new Object[params.size()]));
			totalCount = jdbcTemplate.queryForObject(countsql, params.toArray(new Object[params.size()]),Integer.class);
			resultList = changeMapKeyToLowerCase(resultList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Page resultPage = new Page(resultList, start, length,totalCount);
		return resultPage;
	}
	
	/**
	 * 将查询返回的List的key值改为小写
	 * @param dataList
	 */
	private List<Map<String,Object>> changeMapKeyToLowerCase(List<Map<String,Object>> dataList){
		List<Map<String,Object>> lowerCaseList = new ArrayList<Map<String,Object>>();
		Map<String,Object> lowerCaseMap = null;
		for(Map<String,Object> dataMap : dataList){
			lowerCaseMap = new HashMap<String,Object>();
			for(Entry<String,Object> dataEntry : dataMap.entrySet()){
				lowerCaseMap.put(dataEntry.getKey().toLowerCase(), dataEntry.getValue());
			}
			lowerCaseList.add(lowerCaseMap);
		}
		dataList = null;
		return lowerCaseList;
	}
}
