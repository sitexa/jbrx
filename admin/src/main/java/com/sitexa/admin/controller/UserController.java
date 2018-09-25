package com.sitexa.admin.controller;

import com.gavin.model.Page;
import com.gavin.schema.service.User;
import com.jfinal.aop.Before;
import com.sitexa.admin.interceptor.AuthInterceptor;
import com.sitexa.common.base.BaseController;
import com.sitexa.facade.interfaces.UserService;
import io.jboot.core.rpc.annotation.JbootrpcService;
import io.jboot.web.controller.annotation.RequestMapping;

@RequestMapping(value =  "/manage/user")
public class UserController extends BaseController {
    @JbootrpcService
    private UserService usersService;
    /**
     * 获取会员分页列表
     */
    @Before(AuthInterceptor.class)
    public void getUsersList(){
        Page<User> page=usersService.getUserList(pageNumber(),pageSize(),getParam("mobilePhone"));
        result(page);
    }
    /**
     * 启用/禁用会员
     */
    @Before(AuthInterceptor.class)
    public void isUsedUsers(){
        User user=getBeanByJsonParam(User.class);
        result(usersService.isUsedUser(user));
    }
    /**
     * 删除会员
     */
    @Before(AuthInterceptor.class)
    public void delUsers(){
        User user=getBeanByJsonParam(User.class);
        result(usersService.delUser(user));
    }
}
