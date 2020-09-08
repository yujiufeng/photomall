package org.linlinjava.litemall.wx.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.db.domain.*;
import org.linlinjava.litemall.db.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

/**
 * 用户创建订单时照片服务
 */
@Service
public class WxPhotoService {
    private final Log logger = LogFactory.getLog(WxPhotoService.class);

    @Autowired
    private Environment environment;
    
    @Autowired
    private LitemallOrderPhotoService litemallOrderPhotoService;
    
    public Object upload(LitemallOrderPhoto entity) throws Exception{
    	String basePath = environment.getProperty("litemall.photo.base.path");
    	Object msg = ResponseUtil.ok();
    	//此处开始写上传照片的逻辑，分两步：1.把文件上传至服务器指定目录，2.向表里插入一条数据
    	
    	
    	
    	return msg;
    }

    
}