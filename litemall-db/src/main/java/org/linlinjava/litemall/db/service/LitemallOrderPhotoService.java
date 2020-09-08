package org.linlinjava.litemall.db.service;

import org.linlinjava.litemall.db.common.dao.CommonDao;
import org.linlinjava.litemall.db.common.service.impl.CommonServiceImpl;
import org.linlinjava.litemall.db.dao.LitemallOrderPhotoMapper;
import org.linlinjava.litemall.db.domain.LitemallOrderPhoto;
import org.linlinjava.litemall.db.service.iface.ILitemallOrderPhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LitemallOrderPhotoService extends CommonServiceImpl<LitemallOrderPhoto> implements ILitemallOrderPhotoService{
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private LitemallOrderPhotoMapper litemallOrderPhotoMapper;

    public List<LitemallOrderPhoto> getPhotoList(String orderSn) throws Exception{
    	String hql = "from LitemallOrderPhoto where orderSn = ?1 order by id";
    	List<Object> params = new ArrayList<Object>();
    	params.add(orderSn);
    	return litemallOrderPhotoMapper.findByHql(hql, params);
    }
    
    
    
    
    @Override
	public CommonDao<LitemallOrderPhoto> getCommonDao() {
		return litemallOrderPhotoMapper;
	}
	
	@Autowired
	public void setLitemallOrderPhotoMapper(LitemallOrderPhotoMapper litemallOrderPhotoMapper) {
		this.litemallOrderPhotoMapper = litemallOrderPhotoMapper;
	}

}
