package com.www.mall.controller;

import com.gavin.model.Page;
import com.jfinal.aop.Before;
import com.www.mall.common.base.BaseController;
import com.www.mall.interceptor.AuthInterceptor;
import com.www.mall.user.dto.Tactics;
import com.www.mall.user.interf.TacticsService;
import io.jboot.core.rpc.annotation.JbootrpcService;
import io.jboot.web.controller.annotation.RequestMapping;

@RequestMapping(value =  "/manage/tactics")
public class TacticsController extends BaseController {
    @JbootrpcService
    private TacticsService tacticsService;
    /**
     * 获取策略列表
     */
    @Before(AuthInterceptor.class)
    public void getTacticsList(){
        Page<Tactics> page=tacticsService.getTacticsList(pageNumber(),pageSize(),getAdmins().getPlateformId(),getParam("tacticsName"));
        result(page);
    }

    /**
     * 新增策略信息
     */
    @Before(AuthInterceptor.class)
    public void insertTactics(){
        Tactics tactics=getBeanByJsonParam(Tactics.class);
        tactics.setPlatformId(getAdmins().getPlateformId());
        result(tacticsService.insertTactics(tactics));
    }
    /**
     * 修改策略信息
     */
    @Before(AuthInterceptor.class)
    public void updateTactics(){
        Tactics tactics=getBeanByJsonParam(Tactics.class);
        result(tacticsService.updateTactics(tactics));
    }
    /**
     * 启用/禁用策略信息
     */
    @Before(AuthInterceptor.class)
    public void isUsedTactics(){
        Tactics tactics=getBeanByJsonParam(Tactics.class);
        result(tacticsService.isUsedTactics(tactics));
    }
    /**
     * 删除策略信息
     */
    public void delTactics(){
        Tactics tactics=getBeanByJsonParam(Tactics.class);
        result(tacticsService.delTactics(tactics));
    }
}
