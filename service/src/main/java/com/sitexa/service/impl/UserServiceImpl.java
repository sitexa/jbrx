package com.sitexa.service.impl;

import com.gavin.business.DBTrans;
import com.gavin.model.Page;
import com.gavin.model.Request;
import com.gavin.model.Response;
import com.gavin.schema.service.User;
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
    public Response queryUserByUsersName(String mobilePhone){
        Request request=Request.build(service, "queryUserByUsersName").set("mobilePhone", mobilePhone);
        Response response=DBTrans.execute(request);
        return response;
    }

    @Override
    public Response saveUser(UserPrincipal user){
        Request request=Request.build(service, "saveUser").from(user).currentTime();
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
    public UserPrincipal queryUserById(long id){
        Request request=Request.build(service, "queryUserById").set("id",id);
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

    @Override
    public Page<User> getUserList(int pageNumber, int pageSize, String mobilePhone){
        Request request=Request.build(service, "getUserList").page(pageNumber,pageSize).set("mobilePhone", mobilePhone);
        Page<User> page=DBTrans.page(request, User.class);
        return page;
    }
    @Override
    public Response isUsedUser(User user){
        Request request=Request.build(service, "isUsedUser").from(user).currentTime();
        Response response=DBTrans.execute(request);
        return response;
    }
    @Override
    public Response delUser(User user){
        Request request=Request.build(service, "delUser").from(user).currentTime();
        Response response=DBTrans.execute(request);
        return response;
    }
}
