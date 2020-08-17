package org.linlinjava.litemall.admin.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.linlinjava.litemall.admin.annotation.RequiresPermissionsDesc;
import org.linlinjava.litemall.admin.service.AdminPhotoService;
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
    @RequiresPermissionsDesc(menu = {"商场管理", "订单管理"}, button = "查询")
    @GetMapping("/list")
    public Object list(Integer userId, String orderSn) {
        //return adminPhotoService.list(userId, orderSn);
    	return null;
    }
    
    /**
     * 下载订单照片
     *
     * @param orderSn
     * @return
     */
    @RequiresPermissions("admin:order:list")
    @RequiresPermissionsDesc(menu = {"商场管理", "订单管理"}, button = "查询")
    @GetMapping("/download")
    public Object download(String orderSn) {
        //return adminPhotoService.download(orderSn);
    	return null;
    }

    /**
     * 删除订单照片
     *
     * @param orderSn
     * @return 操作结果
     */
    @RequiresPermissions("admin:order:delete")
    @RequiresPermissionsDesc(menu = {"商场管理", "订单管理"}, button = "订单删除")
    @PostMapping("/delete")
    public Object delete(String orderSn) {
        //return adminPhotoService.delete(orderSn);
    	return null;
    }

    
}
