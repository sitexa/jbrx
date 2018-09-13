package com.www.mall.service.user;

import com.gavin.business.DBTrans;
import com.gavin.model.Page;
import com.gavin.model.Request;
import com.gavin.model.Response;
import com.www.mall.common.base.BaseService;
import com.www.mall.common.shiro.principal.Admins;
import com.xiaoleilu.hutool.util.RandomUtil;
import io.jboot.aop.annotation.Bean;
import io.jboot.core.rpc.annotation.JbootrpcService;
import org.apache.shiro.crypto.hash.Md5Hash;

import javax.inject.Singleton;

@Bean
@Singleton
@JbootrpcService
public class AdminsService extends BaseService implements com.www.mall.user.interf.AdminsService{
    @Override
    public Admins getAdminsByUserName(String adminsName){
        Request request=Request.build(service, "getAdminsByUserName").set("adminsName", adminsName).currentTime();
        Admins admins=DBTrans.bean(request,Admins.class);
        return admins;
    }
    @Override
    public Page<Admins> getAdminsList(int pageNumber, int pageSize, String userName){
        Request request=Request.build(service, "getAdminsList").page(pageNumber, pageSize).set("userName", userName).currentTime();
        Page<Admins> page=DBTrans.page(request,Admins.class);
        return page;
    }
    @Override
    public Response insertAdmin(Admins admins){
        String salt=RandomUtil.simpleUUID();
        admins.setSalt(salt);
        admins.setPassword(new Md5Hash(admins.getPassword(),salt,3).toString());
        Request request=Request.build(service, "insertAdmin").from(admins).currentTime();
        Response response=DBTrans.execute(request);
        return response;
    }
    @Override
    public Response updateAdmin(Admins admins){
        Request request=Request.build(service, "updateAdmin").from(admins).currentTime();
        Response response=DBTrans.execute(request);
        return response;
    }
    @Override
    public Response isUsedAdmin(Admins admins){
        Request request=Request.build(service, "isUsedAdmin").from(admins).currentTime();
        Response response=DBTrans.execute(request);
        return response;
    }
    @Override
    public Response delAdmin(Admins admins){
        Request request=Request.build(service, "delAdmin").from(admins).currentTime();
        Response response=DBTrans.execute(request);
        return response;
    }
    @Override
    public Response updateAdminPassword(Admins admins){
        String salt=RandomUtil.simpleUUID();
        admins.setSalt(salt);
        admins.setPassword(new Md5Hash(admins.getPassword(),salt,3).toString());
        Request request=Request.build(service, "updateAdminPassword").from(admins).currentTime();
        Response response=DBTrans.execute(request);
        return response;
    }
}
