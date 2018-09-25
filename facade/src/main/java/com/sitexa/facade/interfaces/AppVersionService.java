package com.sitexa.facade.interfaces;

import com.gavin.model.Response;
import com.sitexa.facade.vo.AppVersion;

public interface AppVersionService {
    public static final String service = AppVersionService.class.getSimpleName();

    public AppVersion getAppVersion();

    public Response saveAppVersion(AppVersion appVersion);

}
