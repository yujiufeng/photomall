package org.linlinjava.litemall.admin.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.linlinjava.litemall.admin.annotation.RequiresPermissionsDesc;
import org.linlinjava.litemall.admin.service.AdminPhotoService;
import org.linlinjava.litemall.core.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/admin/photo")
@Validated
public class AdminPhotoController {
    private final Log logger = LogFactory.getLog(AdminPhotoController.class);

    @Autowired
    private AdminPhotoService adminPhotoService;

    /**
     * 查询订单照片
     *
     * @param userId
     * @param orderSn
     * @return
     */
    @RequiresPermissions("admin:order:list")
    @RequiresPermissionsDesc(menu = {"订单管理", "订单管理"}, button = "查询")
    @GetMapping("/list")
    public Object list(String orderSn) {
        return adminPhotoService.list(orderSn);
    }
    
    /**
     * 下载订单照片
     *
     * @param orderSn
     * @return
     */
    @RequiresPermissions("admin:order:list")
    @RequiresPermissionsDesc(menu = {"订单管理", "订单管理"}, button = "查询")
    @PostMapping("/download")
    public Object download(String orderSn) {
    	Object result = ResponseUtil.ok();
    	try {
    		String msg = adminPhotoService.download(orderSn);
    		if(!"".equals(msg)) {
    			result = ResponseUtil.fail(-1, msg);
    		}
		} catch (Exception e) {
			e.printStackTrace();
			result = ResponseUtil.fail();
		}
    	return result;
    }

    /**
     * 删除订单照片
     *
     * @param orderSn
     * @return 操作结果
     */
    @RequiresPermissions("admin:order:list")
    @RequiresPermissionsDesc(menu = {"订单管理", "订单管理"}, button = "查询")
    @PostMapping("/delete")
    public Object delete(String id) {
    	Object result = ResponseUtil.ok();
    	try {
    		adminPhotoService.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
			result = ResponseUtil.fail();
		}
    	return result;
    }
    
}
