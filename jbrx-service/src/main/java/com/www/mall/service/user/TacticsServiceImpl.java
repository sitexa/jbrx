package com.www.mall.service.user;

import com.gavin.business.DBTrans;
import com.gavin.model.Page;
import com.gavin.model.Request;
import com.gavin.model.Response;
import com.www.mall.common.base.BaseService;
import com.www.mall.user.dto.Tactics;
import com.www.mall.user.dto.UserTactics;
import com.www.mall.user.vo.TacticsVo;
import io.jboot.aop.annotation.Bean;
import io.jboot.core.rpc.annotation.JbootrpcService;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

@Bean
@Singleton
@JbootrpcService
public class TacticsServiceImpl extends BaseService implements com.www.mall.user.interf.TacticsService{
    @Override
    public Page<TacticsVo> getTacticsAppList(int pageNumber, int pageSize, Long userId, String tacticsName, String minRate, String topRisk){
        Request request=Request.build(service, "getTacticsAppList").page(pageNumber, pageSize).set("tacticsName", tacticsName).set("minRate", minRate).set("topRisk", topRisk);
        Page<TacticsVo> page=DBTrans.page(request,TacticsVo.class);
        List<TacticsVo> list=new ArrayList<>();
        for(int i=0;i<page.getList().size();i++){
            TacticsVo vo=page.getList().get(i);
            Request request2=Request.build(service, "getUserTacticsCount").set("userId", userId).set("tacticsId", vo.getId());
            Response response=DBTrans.execute(request2);
            vo.setFlag(response.getInteger("count"));
            list.add(vo);
        }
        page.getList().clear();
        page.setList(list);
        return page;
    }
    @Override
    public Response followTactics(Long userId,Long tacticsId){
        Request request=Request.build(service, "followTactics").set("userId", userId).set("tacticsId", tacticsId).currentTime();
        Response response=DBTrans.execute(request);
        return response;
    }
    @Override
    public Response cancelFollowTactics(Long userId,Long tacticsId){
        Request request=Request.build(service, "cancelFollowTactics").set("userId", userId).set("tacticsId", tacticsId).currentTime();
        Response response=DBTrans.execute(request);
        return response;
    }
    @Override
    public Page<TacticsVo> getUserTacticsList(int pageNumber,int pageSize,Long userId){
        Request request=Request.build(service, "getUserTacticsList").page(pageNumber, pageSize).set("userId", userId);
        Page<TacticsVo> page=DBTrans.page(request,TacticsVo.class);
        List<TacticsVo> list=new ArrayList<>();
        for(int i=0;i<page.getList().size();i++){
            TacticsVo vo=page.getList().get(i);
            vo.setFlag(1);
            list.add(vo);
        }
        page.getList().clear();
        page.setList(list);
        return page;
    }
    @Override
    public Page<TacticsVo> getTacticsListByPlatformId(int pageNumber,int pageSize,Long userId,Long platformId,String minRate,String topRisk){
        Request request=Request.build(service, "getTacticsListByPlatformId").page(pageNumber, pageSize).set("platformId", platformId).set("minRate", minRate).set("topRisk", topRisk);
        Page<TacticsVo> page=DBTrans.page(request,TacticsVo.class);
        List<TacticsVo> list=new ArrayList<>();
        for(int i=0;i<page.getList().size();i++){
            TacticsVo vo=page.getList().get(i);
            Request request2=Request.build(service, "getUserTacticsCount").set("userId", userId).set("tacticsId", vo.getId());
            Response response=DBTrans.execute(request2);
            vo.setFlag(response.getInteger("count"));
            list.add(vo);
        }
        page.getList().clear();
        page.setList(list);
        return page;
    }

    @Override
    public Page<Tactics> getTacticsList(int pageNumber, int pageSize, Long platformId, String tacticsName){
        Request request=Request.build(service, "getTacticsList").page(pageNumber, pageSize).set("tacticsName", tacticsName).set("platformId", platformId);
        Page<Tactics> page=DBTrans.page(request,Tactics.class);
        return page;
    }
    @Override
    public Response insertTactics(Tactics tactics){
        Request request=Request.build(service, "insertTactics").from(tactics).currentTime();
        Response response=DBTrans.execute(request);
        return response;
    }
    @Override
    public Response updateTactics(Tactics tactics){
        Request request=Request.build(service, "updateTactics").from(tactics).currentTime();
        Response response=DBTrans.execute(request);
        return response;
    }
    @Override
    public Response isUsedTactics(Tactics tactics){
        Request request=Request.build(service, "isUsedTactics").from(tactics).currentTime();
        Response response=DBTrans.execute(request);
        return response;
    }
    @Override
    public Response delTactics(Tactics tactics){
        Request request=Request.build(service, "delTactics").from(tactics).currentTime();
        Response response=DBTrans.execute(request);
        return response;
    }
}
