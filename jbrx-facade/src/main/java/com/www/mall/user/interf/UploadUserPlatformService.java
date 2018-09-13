package com.www.mall.user.interf;

import com.gavin.model.Page;
import com.gavin.model.Response;
import com.www.mall.user.dto.UploadUserPlatform;

public interface UploadUserPlatformService {
    public final static String service=UploadUserPlatformService.class.getSimpleName();

    public Response followUploadUserPlatform(Long userId, Long platformId);

    public Page<UploadUserPlatform> getUploadUserPlatformList(int pageNumber, int pageSize,Long userId, String platformName);
}
