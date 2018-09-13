package com.www.mall.service.user;

import com.gavin.business.DBTrans;
import com.gavin.model.Request;
import com.gavin.model.Response;
import com.www.mall.common.base.BaseService;
import com.www.mall.common.shiro.principal.User;
import io.jboot.aop.annotation.Bean;
import io.jboot.core.rpc.annotation.JbootrpcService;

import javax.inject.Singleton;

@Bean
@Singleton
@JbootrpcService
public class UsersService extends BaseService implements com.www.mall.user.interf.UsersService{
    @Override
    public Response queryUsersByUsersName(String mobilePhone){
        Request request=Request.build(service, "queryUsersByUsersName").set("mobilePhone", mobilePhone);
        Response response=DBTrans.execute(request);
        return response;
    }

    @Override
    public Response saveUsers(User user){
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
    public User queryUsersById(long id){
        Request request=Request.build(service, "queryUsersById").set("id",id);
        User user=DBTrans.bean(request,User.class);
        return user;
    }
    @Override
    public Response updatePassword(long userId,String newPassword, String newSalt){
        Request request=Request.build(service, "updatePassword").set("userId",userId).set("newPassword", newPassword).set("newSalt",newSalt ).currentTime();
        Response response=DBTrans.execute(request);
        return response;
    }


    @Override
    public Response updateBankInfo(User user){
        //Request request=Request.build(service, "updateBankInfo").set("userId",user.getId()).set("bank", user.getBank()).set("bankch", user.getBankch()).set("bank_num", user.getBankNum()).set("bank_img", user.getBankImg()).set("bankcard_owner", user.getBankcardOwner()).set("mobile", user.getMobile()).currentTime();
        Request request=Request.build(service, "updateBankInfo").from(user).currentTime();
        Response response=DBTrans.execute(request);
        return response;
    }

    @Override
    public Response updateUserIdcardInfo(User user){
        //Request request=Request.build(service, "updateBankInfo").set("userId",user.getId()).set("bank", user.getBank()).set("bankch", user.getBankch()).set("bank_num", user.getBankNum()).set("bank_img", user.getBankImg()).set("bankcard_owner", user.getBankcardOwner()).set("mobile", user.getMobile()).currentTime();
        Request request=Request.build(service, "updateUserIdcardInfo").from(user).currentTime();
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
    public Response updateUserAddress(User user){
        //Request request=Request.build(service, "updateBankInfo").set("userId",user.getId()).set("bank", user.getBank()).set("bankch", user.getBankch()).set("bank_num", user.getBankNum()).set("bank_img", user.getBankImg()).set("bankcard_owner", user.getBankcardOwner()).set("mobile", user.getMobile()).currentTime();
        Request request=Request.build(service, "updateUserAddress").from(user).currentTime();
        Response response=DBTrans.execute(request);
        return response;
    }

    @Override
    public Response deleteBankInfo(Long userId){
        Request request=Request.build(service, "deleteBankInfo").set("userId",userId).currentTime();
        Response response=DBTrans.execute(request);
        return response;
    }


}