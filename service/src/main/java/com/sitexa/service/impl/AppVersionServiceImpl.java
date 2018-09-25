package com.sitexa.service.impl;

import com.gavin.business.DBTrans;
import com.gavin.model.Request;
import com.gavin.model.Response;
import com.sitexa.common.base.BaseService;
import com.sitexa.facade.interfaces.AppVersionService;
import com.sitexa.facade.vo.AppVersion;
import io.jboot.aop.annotation.Bean;
import io.jboot.core.rpc.annotation.JbootrpcService;

import javax.inject.Singleton;

@Bean
@Singleton
@JbootrpcService
public class AppVersionServiceImpl extends BaseService implements AppVersionService {
    @Override
    public AppVersion getAppVersion() {
        Request request = Request.build(service, "getAppVersion");
        return DBTrans.bean(request,AppVersion.class);
    }

    @Override
    public Response saveAppVersion(AppVersion appVersion) {
        Request request = Request.build(service, "saveAppVersion").from(appVersion).currentTime();
        return DBTrans.execute(request);
    }
}
