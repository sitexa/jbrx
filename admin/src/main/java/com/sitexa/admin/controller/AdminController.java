package com.sitexa.admin.controller;

import com.gavin.model.Page;
import com.jfinal.aop.Before;
import com.sitexa.admin.interceptor.AuthInterceptor;
import com.sitexa.common.base.BaseController;
import com.sitexa.common.shiro.principal.AdminPrincipal;
import com.sitexa.facade.interfaces.AdminService;
import io.jboot.core.rpc.annotation.JbootrpcService;
import io.jboot.web.controller.annotation.RequestMapping;

@RequestMapping(value =  "/manage/admin")
public class AdminController extends BaseController {
    @JbootrpcService
    private AdminService adminService;
    /**
     * 获取系统管理员列表
     */
    @Before(AuthInterceptor.class)
    public void getAdminList(){
        Page<AdminPrincipal> page= adminService.getAdminsList(pageNumber(),pageSize(),getParam("userName"));
        result(page);
    }
    /**
     * 新增系统管理员信息
     */
    @Before(AuthInterceptor.class)
    public void insertAdmin(){
        AdminPrincipal admins=getBeanByJsonParam(AdminPrincipal.class);
        result(adminService.insertAdmin(admins));
    }
    /**
     * 修改系统管理员信息
     */
    @Before(AuthInterceptor.class)
    public void updateAdmin(){
        AdminPrincipal admins=getBeanByJsonParam(AdminPrincipal.class);
        result(adminService.updateAdmin(admins));
    }
    /**
     * 启用/禁用系统管理员信息
     */
    @Before(AuthInterceptor.class)
    public void isUsedAdmin(){
        AdminPrincipal admins=getBeanByJsonParam(AdminPrincipal.class);
        result(adminService.isUsedAdmin(admins));
    }
    /**
     * 删除系统管理员信息
     */
    @Before(AuthInterceptor.class)
    public void delAdmin(){
        AdminPrincipal admins=getBeanByJsonParam(AdminPrincipal.class);
        result(adminService.delAdmin(admins));
    }
    /**
     * 修改密码
     */
    @Before(AuthInterceptor.class)
    public void updateAdminPassword(){
        AdminPrincipal admins=getBeanByJsonParam(AdminPrincipal.class);
        result(adminService.updateAdminPassword(admins));

    }
}
