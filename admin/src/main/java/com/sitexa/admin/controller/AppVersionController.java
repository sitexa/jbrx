package com.sitexa.admin.controller;

import com.gavin.model.Response;
import com.jfinal.aop.Before;
import com.sitexa.admin.interceptor.AuthInterceptor;
import com.sitexa.common.base.BaseController;
import com.sitexa.facade.interfaces.AppVersionService;
import com.sitexa.facade.vo.AppVersion;
import io.jboot.core.rpc.annotation.JbootrpcService;
import io.jboot.web.controller.annotation.RequestMapping;

@RequestMapping("/manage/version")
public class AppVersionController extends BaseController {

    @JbootrpcService
    private AppVersionService appVersionService;

    public void index(){
        AppVersion appVersion = appVersionService.getAppVersion();
        result(appVersion);
    }

    @Before(AuthInterceptor.class)
    public void saveVersion(){
        AppVersion appVersion = getBeanByJsonParam(AppVersion.class);
        Response response = appVersionService.saveAppVersion(appVersion);
        result(response);
    }

}
