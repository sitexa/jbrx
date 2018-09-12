package com.www.mall.controller.api;

import com.gavin.model.Page;
import com.www.mall.common.base.BaseController;
import com.www.mall.user.dto.Platform;
import com.www.mall.user.interf.PlatformService;
import com.www.mall.user.vo.PlatformVo;
import io.jboot.core.rpc.annotation.JbootrpcService;
import io.jboot.web.controller.annotation.RequestMapping;

@RequestMapping(value =  "/app/platform")
public class PlatformController extends BaseController {
    @JbootrpcService
    private PlatformService platformService;

    /**
     * 获取平台列表
     */
    public void getPlatformAppList(){
        Page<PlatformVo> page=platformService.getPlatformAppList(pageNumber(),pageSize(),getParamToLong("userId"),getParam("platformName"));
        result(page);
    }
    /**
     * 关注平台
     */
    public void followPlatform(){
        result(platformService.followPlatform(getParamToLong("userId"),getParamToLong("platformId")));
    }
    /**
     * 取消关注平台
     */
    public void cancelFollowPlatform(){
        result(platformService.cancelFollowPlatform(getParamToLong("userId"),getParamToLong("platformId")));
    }
    /**
     * 获取用户关注平台列表
     */
    public void getUserPlatformList(){
        Page<PlatformVo> page=platformService.getUserPlatformList(pageNumber(),pageSize(),getParamToLong("userId"));
        result(page);
    }
    /**
     * 获取推荐平台列表
     */
    public void getRecommendPlatformList(){
        Page<PlatformVo> page=platformService.getRecommendPlatformList(pageNumber(),pageSize(),getParamToLong("userId"));
        result(page);
    }
}
