package org.linlinjava.litemall.wx.service;

import org.linlinjava.litemall.db.domain.LitemallOrderPhoto;
import org.linlinjava.litemall.db.service.LitemallOrderPhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户创建订单时照片服务
 */
@Service
public class WxPhotoService {

    @Autowired
    private LitemallOrderPhotoService litemallOrderPhotoService;
    
    public void upload(LitemallOrderPhoto entity) throws Exception{
    	litemallOrderPhotoService.save(entity);
    }
}