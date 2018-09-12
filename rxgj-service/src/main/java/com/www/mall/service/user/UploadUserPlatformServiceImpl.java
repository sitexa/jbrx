package com.www.mall.service.user;

import com.gavin.business.DBTrans;
import com.gavin.model.Page;
import com.gavin.model.Request;
import com.gavin.model.Response;
import com.www.mall.common.base.BaseService;
import com.www.mall.user.dto.UploadUserPlatform;
import com.www.mall.user.interf.UploadUserPlatformService;
import io.jboot.aop.annotation.Bean;
import io.jboot.core.rpc.annotation.JbootrpcService;

import javax.inject.Singleton;

@Bean
@Singleton
@JbootrpcService
public class UploadUserPlatformServiceImpl extends BaseService implements UploadUserPlatformService {

    /**
     * 上传平台的用户 新增
     * @param userId
     * @param platformId
     * @return
     */
    @Override
    public Response followUploadUserPlatform(Long userId, Long platformId){
        Request request=Request.build(service, "followUploadUserPlatform").set("userId", userId).set("platformId", platformId).currentTime();
        Response response=DBTrans.execute(request);
        return response;
    }
    @Override
    public Page<UploadUserPlatform> getUploadUserPlatformList(int pageNumber, int pageSize,Long userId, String platformName){
        Request request=Request.build(service, "getUploadUserPlatformList").page(pageNumber, pageSize).set("userId", userId).set("platformName", platformName);
        Page<UploadUserPlatform> page=DBTrans.page(request,UploadUserPlatform.class);
        return page;
    }

}
