package com.www.mall.controller;

import com.gavin.model.Page;
import com.jfinal.aop.Before;
import com.www.mall.common.base.BaseController;
import com.www.mall.interceptor.AuthInterceptor;
import com.www.mall.user.dto.Platform;
import com.www.mall.user.interf.PlatformService;
import io.jboot.core.rpc.annotation.JbootrpcService;
import io.jboot.web.controller.annotation.RequestMapping;

@RequestMapping(value =  "/manage/platform")
public class PlatformController extends BaseController {
    @JbootrpcService
    private PlatformService platformService;
    /**
     * 获取平台列表
     */
    @Before(AuthInterceptor.class)
    public void getPlatformList(){
        Page<Platform> page=platformService.getPlatformList(pageNumber(),pageSize(),getParam("platformName"));
        result(page);
    }
    /**
     * 新增平台信息
     */
    @Before(AuthInterceptor.class)
    public void insertPlatform(){
        Platform platform=getBeanByJsonParam(Platform.class);
        result(platformService.insertPlatform(platform));
    }
    /**
     * 修改平台信息
     */
    @Before(AuthInterceptor.class)
    public void updatePlatform(){
        Platform platform=getBeanByJsonParam(Platform.class);
        result(platformService.updatePlatform(platform));
    }
    /**
     * 启用/禁用平台信息
     */
    @Before(AuthInterceptor.class)
    public void isUsedPlatform(){
        Platform platform=getBeanByJsonParam(Platform.class);
        result(platformService.isUsedPlatform(platform));
    }
    /**
     * 删除平台信息
     */
    @Before(AuthInterceptor.class)
    public void delPlatform(){
        Platform platform=getBeanByJsonParam(Platform.class);
        result(platformService.delPlatform(platform));
    }
}
