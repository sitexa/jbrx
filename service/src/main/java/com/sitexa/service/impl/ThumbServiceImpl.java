package com.sitexa.service.impl;

import javax.inject.Singleton;

import com.gavin.business.DBTrans;
import com.gavin.model.Request;
import com.gavin.model.Response;

import com.sitexa.common.base.BaseService;
import com.sitexa.facade.interfaces.ThumbService;
import com.sitexa.facade.vo.Thumb;
import io.jboot.aop.annotation.Bean;
import io.jboot.core.rpc.annotation.JbootrpcService;

@Bean
@Singleton
@JbootrpcService
public class ThumbServiceImpl extends BaseService implements ThumbService {

    /**
     * 保存点赞数据
     */
    @Override
    public Response saveThumb(Thumb thumb) {
        Request requests = Request.build(SERVICE, QUERYCOUNTBYVIDEOID).set("userId", thumb.getUserId()).set("type", thumb.getType()).set("id", thumb.getAId());
        Response count = DBTrans.execute(requests);
        if (Integer.parseInt(count.get("totalCount").toString()) > 0) {
            return new Response(-1, "你已点过赞了.");
        } else {
            Request request = Request.build(SERVICE, SAVE).from(thumb).currentTime();
            Response response = DBTrans.execute(request);
            return response;
        }
    }

    /**
     * 查询出视频的总点赞数
     */
    @Override
    public Integer queryCountByVideoId(Long id, Integer type, String userId) {
        Request request;
        if (userId == null) {
            request = Request.build(SERVICE, QUERYCOUNTBYVIDEOID).set("id", id).set("type", type);
        } else {
            request = Request.build(SERVICE, QUERYCOUNTBYVIDEOID).set("id", id).set("type", type).set("userId", userId);
        }
        Response count = DBTrans.execute(request);
        return Integer.parseInt(count.get("totalCount").toString());
    }

}