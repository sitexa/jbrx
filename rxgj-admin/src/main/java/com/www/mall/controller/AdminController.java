package com.www.mall.controller;

import com.gavin.model.Page;
import com.jfinal.aop.Before;
import com.www.mall.common.base.BaseController;
import com.www.mall.common.shiro.principal.Admins;
import com.www.mall.interceptor.AuthInterceptor;
import com.www.mall.user.dto.Platform;
import com.www.mall.user.interf.AdminsService;
import com.www.mall.user.interf.PlatformService;
import io.jboot.core.rpc.annotation.JbootrpcService;
import io.jboot.web.controller.annotation.RequestMapping;

import java.util.List;

@RequestMapping(value =  "/manage/admin")
public class AdminController extends BaseController {
    @JbootrpcService
    private AdminsService adminsService;
    @JbootrpcService
    private PlatformService platformService;
    /**
     * 获取系统管理员列表
     */
    @Before(AuthInterceptor.class)
    public void getAdminList(){
        Page<Admins> page=adminsService.getAdminsList(pageNumber(),pageSize(),getParam("userName"));
        result(page);
    }
    /**
     * 获取平台信息列表
     */
    @Before(AuthInterceptor.class)
    public void getPlatformInfoList(){
        List<Platform> list=platformService.getPlatformInfoList();
        result(list);
    }
    /**
     * 新增系统管理员信息
     */
    @Before(AuthInterceptor.class)
    public void insertAdmin(){
        Admins admins=getBeanByJsonParam(Admins.class);
        result(adminsService.insertAdmin(admins));
    }
    /**
     * 修改系统管理员信息
     */
    @Before(AuthInterceptor.class)
    public void updateAdmin(){
        Admins admins=getBeanByJsonParam(Admins.class);
        result(adminsService.updateAdmin(admins));
    }
    /**
     * 启用/禁用系统管理员信息
     */
    @Before(AuthInterceptor.class)
    public void isUsedAdmin(){
        Admins admins=getBeanByJsonParam(Admins.class);
        result(adminsService.isUsedAdmin(admins));
    }
    /**
     * 删除系统管理员信息
     */
    @Before(AuthInterceptor.class)
    public void delAdmin(){
        Admins admins=getBeanByJsonParam(Admins.class);
        result(adminsService.delAdmin(admins));
    }
    /**
     * 修改密码
     */
    @Before(AuthInterceptor.class)
    public void updateAdminPassword(){
        Admins admins=getBeanByJsonParam(Admins.class);
        result(adminsService.updateAdminPassword(admins));

    }
}
