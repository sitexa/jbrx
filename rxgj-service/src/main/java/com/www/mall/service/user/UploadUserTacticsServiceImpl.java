package com.www.mall.service.user;

import com.gavin.business.DBTrans;
import com.gavin.model.Page;
import com.gavin.model.Request;
import com.gavin.model.Response;
import com.www.mall.common.base.BaseService;
import com.www.mall.user.dto.UploadUserPlatform;
import com.www.mall.user.dto.UploadUserTactics;
import com.www.mall.user.interf.UploadUserTacticsService;
import io.jboot.aop.annotation.Bean;
import io.jboot.core.rpc.annotation.JbootrpcService;

import javax.inject.Singleton;

@Bean
@Singleton
@JbootrpcService
public class UploadUserTacticsServiceImpl extends BaseService implements UploadUserTacticsService {
    /**
     * 上传策略的用户 新增
     * @param userId
     * @param tacticsId
     * @return
     */
    @Override
    public Response followUploadUserTactics(Long userId, Long tacticsId){
        Request request=Request.build(service, "followUploadUserTactics").set("userId", userId).set("tacticsId", tacticsId).currentTime();
        Response response=DBTrans.execute(request);
        return response;
    }

    @Override
    public Page<UploadUserTactics> getUploadUserTacticsList(int pageNumber, int pageSize, Long userId, String tacticsName){
        Request request=Request.build(service, "getUploadUserTacticsList").page(pageNumber, pageSize).set("userId", userId).set("tacticsName", tacticsName);
        Page<UploadUserTactics> page=DBTrans.page(request,UploadUserPlatform.class);
        return page;
    }
}

