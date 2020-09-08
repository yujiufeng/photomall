package org.linlinjava.litemall.db.service.iface;

import java.util.List;

import org.linlinjava.litemall.db.domain.LitemallOrderPhoto;

public interface ILitemallOrderPhotoService {
	
	public List<LitemallOrderPhoto> getPhotoList(String orderSn) throws Exception;

}
