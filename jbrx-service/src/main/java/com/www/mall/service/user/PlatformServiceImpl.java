package com.www.mall.service.user;

import com.gavin.business.DBTrans;
import com.gavin.model.Page;
import com.gavin.model.Request;
import com.gavin.model.Response;
import com.www.mall.common.base.BaseService;
import com.www.mall.user.dto.Platform;
import com.www.mall.user.dto.RecommendPlatform;
import com.www.mall.user.dto.UserPlatform;
import com.www.mall.user.vo.PlatformVo;
import io.jboot.aop.annotation.Bean;
import io.jboot.core.rpc.annotation.JbootrpcService;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

@Bean
@Singleton
@JbootrpcService
public class PlatformServiceImpl extends BaseService implements com.www.mall.user.interf.PlatformService{
    @Override
    public Page<PlatformVo> getPlatformAppList(int pageNumber, int pageSize,Long userId, String plateformName){
        Request request=Request.build(service, "getPlatformAppList").page(pageNumber, pageSize).set("plateformName", plateformName);
        Page<PlatformVo> page=DBTrans.page(request,PlatformVo.class);
        List<PlatformVo> list=new ArrayList<>();
        for(int i=0;i<page.getList().size();i++){
            PlatformVo vo=page.getList().get(i);
            Request request2=Request.build(service, "getUserPlatformCount").set("userId", userId).set("platformId", vo.getId());
            Response response=DBTrans.execute(request2);
            vo.setFlag(response.getInteger("count"));
            list.add(vo);
        }
        page.getList().clear();;
        page.setList(list);
        return page;
    }
    @Override
    public Response followPlatform(Long userId,Long platformId){
        Request request=Request.build(service, "followPlatform").set("userId", userId).set("platformId", platformId).currentTime();
        Response response=DBTrans.execute(request);
        return response;
    }
    @Override
    public Response cancelFollowPlatform(Long userId,Long platformId){
        Request request=Request.build(service, "cancelFollowPlatform").set("userId", userId).set("platformId", platformId).currentTime();
        Response response=DBTrans.execute(request);
        return response;
    }
    @Override
    public Page<PlatformVo> getUserPlatformList(int pageNumber,int pageSize,Long userId){
        Request request=Request.build(service, "getUserPlatformList").page(pageNumber, pageSize).set("userId", userId);
        Page<UserPlatform> page=DBTrans.page(request,UserPlatform.class);
        Page<PlatformVo> voPage=new Page<>();
        List<PlatformVo> list=new ArrayList<>();
        for(int i=0;i<page.getList().size();i++){
            UserPlatform user=page.getList().get(i);
            PlatformVo vo=new PlatformVo();
            vo.setFlag(1);
            vo.setDay(user.getDay());
            vo.setDescx(user.getDescx());
            vo.setId(user.getPlatformId());
            vo.setInvestMoney(user.getInvestMoney());
            vo.setName(user.getName());
            vo.setShareMoney(user.getShareMoney());
            vo.setUrl(user.getUrl());
            list.add(vo);
        }
        voPage.setTotalPage(page.getTotalPage());
        voPage.setTotalCount(page.getTotalPage());
        voPage.setPageNumber(page.getPageNumber());
        voPage.setPageSize(page.getPageSize());
        voPage.setList(list);
        return voPage;
    }
    @Override
    public Page<PlatformVo> getRecommendPlatformList(int pageNumber,int pageSize,Long userId){
        Request request=Request.build(service, "getRecommendPlatformList").page(pageNumber, pageSize);
        Page<RecommendPlatform> page=DBTrans.page(request,RecommendPlatform.class);
        Page<PlatformVo> voPage=new Page<>();
        List<PlatformVo> list=new ArrayList<>();
        for(int i=0;i<page.getList().size();i++){
            RecommendPlatform platform=page.getList().get(i);
            PlatformVo vo=new PlatformVo();
            Request request2=Request.build(service, "getUserPlatformCount").set("userId", userId).set("platformId", platform.getPlatformId());
            Response response=DBTrans.execute(request2);
            vo.setId(platform.getPlatformId());
            vo.setUrl(platform.getUrl());
            vo.setShareMoney(platform.getShareMoney());
            vo.setName(platform.getName());
            vo.setInvestMoney(platform.getInvestMoney());
            vo.setDescx(platform.getDescx());
            vo.setDay(platform.getDay());
            vo.setFlag(response.getInteger("count"));
            list.add(vo);
        }
        voPage.setTotalPage(page.getTotalPage());
        voPage.setTotalCount(page.getTotalPage());
        voPage.setPageNumber(page.getPageNumber());
        voPage.setPageSize(page.getPageSize());
        voPage.setList(list);
        return voPage;
    }
    @Override
    public Page<Platform> getPlatformList(int pageNumber,int pageSize,String platformName){
        Request request=Request.build(service, "getPlatformList").page(pageNumber, pageSize).set("platformName", platformName);
        Page<Platform> page=DBTrans.page(request,Platform.class);
        return page;
    }
    @Override
    public Response insertPlatform(Platform platform){
        Request request=Request.build(service, "insertPlatform").from(platform).currentTime();
        Response response=DBTrans.execute(request);
        return response;
    }
    @Override
    public Response updatePlatform(Platform platform){
        Request request=Request.build(service, "updatePlatform").from(platform).currentTime();
        Response response=DBTrans.execute(request);
        return response;
    }
    @Override
    public Response isUsedPlatform(Platform platform){
        Request request=Request.build(service, "isUsedPlatform").from(platform).currentTime();
        Response response=DBTrans.execute(request);
        return response;
    }
    @Override
    public Response delPlatform(Platform platform){
        Request request=Request.build(service, "delPlatform").from(platform).currentTime();
        Response response=DBTrans.execute(request);
        return response;
    }
    @Override
    public List<Platform> getPlatformInfoList(){
        Request request=Request.build(service, "getPlatformInfoList");
        List<Platform> list=DBTrans.list(request,PlatformVo.class);
        return list;
    }
}
