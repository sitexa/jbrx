package com.sitexa.service.impl;

import com.gavin.business.DBTrans;
import com.gavin.model.Request;
import com.gavin.model.Response;
import com.sitexa.common.base.BaseService;
import com.sitexa.common.shiro.principal.UserPrincipal;
import com.sitexa.facade.interfaces.UserService;
import io.jboot.aop.annotation.Bean;
import io.jboot.core.rpc.annotation.JbootrpcService;

import javax.inject.Singleton;

@Bean
@Singleton
@JbootrpcService
public class UserServiceImpl extends BaseService implements UserService {
    @Override
    public Response queryUsersByUsersName(String mobilePhone){
        Request request=Request.build(service, "queryUsersByUsersName").set("mobilePhone", mobilePhone);
        Response response=DBTrans.execute(request);
        return response;
    }

    @Override
    public Response saveUsers(UserPrincipal user){
        Request request=Request.build(service, "saveUsers").from(user).currentTime();
        Response response=DBTrans.execute(request);
        return response;
    }
    @Override
    public Response queryUserNameExist(String userName){
        Request request=Request.build(service, "queryUserNameExist").set("userName",userName );
        Response response=DBTrans.execute(request);
        return response;
    }
    @Override
    public UserPrincipal queryUsersById(long id){
        Request request=Request.build(service, "queryUsersById").set("id",id);
        UserPrincipal user=DBTrans.bean(request, UserPrincipal.class);
        return user;
    }
    @Override
    public Response updatePassword(long userId,String newPassword, String newSalt){
        Request request=Request.build(service, "updatePassword").set("userId",userId).set("newPassword", newPassword).set("newSalt",newSalt ).currentTime();
        Response response=DBTrans.execute(request);
        return response;
    }

    @Override
    public Response updateUserInfo(String columnName,String columnValue,String columnStr,Long userId){
        Request request=Request.build(service, "updateUserInfo").set("columnName",columnName).set("columnValue",columnValue).set("userId",userId).set("columnStr",columnStr).currentTime();
        Response response=DBTrans.execute(request);
        return response;
    }
}
