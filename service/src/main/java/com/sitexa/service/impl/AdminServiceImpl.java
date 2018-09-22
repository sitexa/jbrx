package com.sitexa.service.impl;

import com.gavin.business.DBTrans;
import com.gavin.model.Page;
import com.gavin.model.Request;
import com.gavin.model.Response;
import com.sitexa.common.base.BaseService;
import com.sitexa.common.shiro.principal.AdminPrincipal;
import com.sitexa.facade.interfaces.AdminService;
import com.xiaoleilu.hutool.util.RandomUtil;
import io.jboot.aop.annotation.Bean;
import io.jboot.core.rpc.annotation.JbootrpcService;
import org.apache.shiro.crypto.hash.Md5Hash;

import javax.inject.Singleton;

@Bean
@Singleton
@JbootrpcService
public class AdminServiceImpl extends BaseService implements AdminService {
    @Override
    public AdminPrincipal getAdminsByUserName(String adminsName) {
        Request request = Request.build(service, "getAdminsByUserName").set("adminsName", adminsName).currentTime();
        AdminPrincipal admins = DBTrans.bean(request, AdminPrincipal.class);
        return admins;
    }

    @Override
    public Page<AdminPrincipal> getAdminsList(int pageNumber, int pageSize, String userName) {
        Request request = Request.build(service, "getAdminsList").page(pageNumber, pageSize).set("userName", userName).currentTime();
        Page<AdminPrincipal> page = DBTrans.page(request, AdminPrincipal.class);
        return page;
    }

    @Override
    public Response insertAdmin(AdminPrincipal admins) {
        String salt = RandomUtil.simpleUUID();
        admins.setSalt(salt);
        admins.setPassword(new Md5Hash(admins.getPassword(), salt, 3).toString());
        Request request = Request.build(service, "insertAdmin").from(admins).currentTime();
        Response response = DBTrans.execute(request);
        return response;
    }

    @Override
    public Response updateAdmin(AdminPrincipal admins) {
        Request request = Request.build(service, "updateAdmin").from(admins).currentTime();
        Response response = DBTrans.execute(request);
        return response;
    }

    @Override
    public Response isUsedAdmin(AdminPrincipal admins) {
        Request request = Request.build(service, "isUsedAdmin").from(admins).currentTime();
        Response response = DBTrans.execute(request);
        return response;
    }

    @Override
    public Response delAdmin(AdminPrincipal admins) {
        Request request = Request.build(service, "delAdmin").from(admins).currentTime();
        Response response = DBTrans.execute(request);
        return response;
    }

    @Override
    public Response updateAdminPassword(AdminPrincipal admins) {
        String salt = RandomUtil.simpleUUID();
        admins.setSalt(salt);
        admins.setPassword(new Md5Hash(admins.getPassword(), salt, 3).toString());
        Request request = Request.build(service, "updateAdminPassword").from(admins).currentTime();
        Response response = DBTrans.execute(request);
        return response;
    }
}
