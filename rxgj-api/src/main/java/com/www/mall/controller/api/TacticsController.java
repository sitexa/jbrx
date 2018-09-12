package com.www.mall.controller.api;

import com.gavin.model.Page;
import com.www.mall.common.base.BaseController;
import com.www.mall.user.dto.Tactics;
import com.www.mall.user.interf.TacticsService;
import com.www.mall.user.vo.TacticsVo;
import io.jboot.core.rpc.annotation.JbootrpcService;
import io.jboot.web.controller.annotation.RequestMapping;

@RequestMapping(value =  "/app/tactics")
public class TacticsController extends BaseController {
    @JbootrpcService
    private TacticsService tacticsService;

    /**
     * 获取策略列表
     */
    public void getTacticsAppList(){
        Page<TacticsVo> page=tacticsService.getTacticsAppList(pageNumber(),pageSize(),getParamToLong("userId"),getParam("tacticsName"),getParam("minRate"),getParam("topRisk"));
        result(page);
    }
    /**
     * 关注策略
     */
    public void followTactics(){
        result(tacticsService.followTactics(getParamToLong("userId"),getParamToLong("tacticsId")));
    }
    /**
     * 取消关注策略
     */
    public void cancelFollowTactics(){
        result(tacticsService.cancelFollowTactics(getParamToLong("userId"),getParamToLong("tacticsId")));
    }
    /**
     * 获取用户关注的策略列表
     */
    public void getUserTacticsList(){
        Page<TacticsVo> page=tacticsService.getUserTacticsList(pageNumber(),pageSize(),getParamToLong("userId"));
        result(page);
    }
    /**
     * 获取平台下的策略列表
     */
    public void getTacticsListByPlatformId(){
        Page<TacticsVo> page=tacticsService.getTacticsListByPlatformId(pageNumber(),pageSize(),getParamToLong("userId"),getParamToLong("platformId"),getParam("minRate"),getParam("topRisk"));
        result(page);
    }
}
