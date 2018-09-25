package com.sitexa.api.controller;

import com.sitexa.common.base.BaseController;
import com.sitexa.facade.interfaces.AppVersionService;
import com.sitexa.facade.vo.AppVersion;
import io.jboot.core.rpc.annotation.JbootrpcService;
import io.jboot.web.controller.annotation.RequestMapping;

@RequestMapping("/app/version")
public class AppVersionController extends BaseController {

    @JbootrpcService
    private AppVersionService appVersionService;

    public void index() {
        AppVersion appVersion = appVersionService.getAppVersion();
        result(appVersion);
    }
}
