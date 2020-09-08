package org.linlinjava.litemall.admin.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.linlinjava.litemall.admin.util.ZipUtils;
import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.db.domain.*;
import org.linlinjava.litemall.db.service.LitemallOrderPhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service

public class AdminPhotoService {
    private final Log logger = LogFactory.getLog(AdminPhotoService.class);
    
    @Autowired
    private Environment environment;
    
    @Autowired
    private LitemallOrderPhotoService litemallOrderPhotoService;

    public Object list(String orderSn) {
        List<LitemallOrderPhoto> photoList = new ArrayList<LitemallOrderPhoto>();
        try {
        	photoList = litemallOrderPhotoService.getPhotoList(orderSn);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return ResponseUtil.okList(photoList);
    }

    public String download(String orderSn) throws Exception{
    	String basePath = environment.getProperty("litemall.photo.base.path");
    	String msg = "";
    	List<LitemallOrderPhoto> photoList = litemallOrderPhotoService.getPhotoList(orderSn);
    	if(null==photoList||photoList.size()==0) {
    		msg = "无法下载，该订单下暂无照片";
    	}else {
    		ZipUtils.compressToZip(basePath+File.separator+orderSn, basePath+"/zip", orderSn+".zip");
    	}
    	return msg;
    }

    public void delete(String id) throws Exception{
    	LitemallOrderPhoto entity = litemallOrderPhotoService.getById(id);
    	litemallOrderPhotoService.delete(entity);
    }
}
