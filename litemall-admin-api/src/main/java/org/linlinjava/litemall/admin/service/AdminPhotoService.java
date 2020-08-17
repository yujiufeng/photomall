package org.linlinjava.litemall.admin.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.db.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service

public class AdminPhotoService {
    private final Log logger = LogFactory.getLog(AdminPhotoService.class);

    public Object list(Integer userId, String orderSn) {
        List<LitemallOrderPhoto> photoList = new ArrayList<LitemallOrderPhoto>();
        
        return ResponseUtil.okList(photoList);
    }

    public Object download(String orderSn) {
        Map<String, Object> data = new HashMap<>();
        
        return ResponseUtil.ok(data);
    }

    public Object delete(String orderSn) {
        Map<String, Object> data = new HashMap<>();
        
        return ResponseUtil.ok(data);
    }

}
