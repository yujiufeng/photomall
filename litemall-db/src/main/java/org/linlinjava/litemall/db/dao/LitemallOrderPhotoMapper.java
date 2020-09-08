package org.linlinjava.litemall.db.dao;

import java.util.ArrayList;
import java.util.List;

import org.linlinjava.litemall.db.common.dao.CommonDao;
import org.linlinjava.litemall.db.domain.LitemallOrderPhoto;
import org.springframework.stereotype.Repository;

@Repository
public class LitemallOrderPhotoMapper extends CommonDao<LitemallOrderPhoto>{

	@Override
	protected String getPageCountHql(LitemallOrderPhoto entity) {
		StringBuffer hql = new StringBuffer();
		hql.append("select count(*) from LitemallOrderPhoto t where 1=1");
		addWhereCondition(hql,entity);
		return hql.toString();
	}

	@Override
	protected String getPageListHql(LitemallOrderPhoto entity) {
		StringBuffer hql = new StringBuffer();
		hql.append(" from LitemallOrderPhoto t where 1=1 ");
		addWhereCondition(hql,entity);
		hql.append(" order by t.create_time desc");
		return hql.toString();
	}

	@Override
	protected List<Object> getPageListParams(LitemallOrderPhoto entity) {
		List<Object> params = new ArrayList<Object>();
		return params;
	}
	
	private void addWhereCondition(StringBuffer hql,LitemallOrderPhoto entity){
		
	}
	
}