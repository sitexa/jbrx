package com.sitexa.api.controller;

import com.gavin.model.Response;
import com.jfinal.aop.Clear;
import com.sitexa.common.base.BaseController;
import com.sitexa.common.base.BaseImgUrl;
import com.sitexa.common.bean.Ret;
import com.sitexa.common.bean.UserVo;
import com.sitexa.common.constants.UserState;
import com.sitexa.common.shiro.cache.CacheKey;
import com.sitexa.common.shiro.cache.ShiroCacheUtils;
import com.sitexa.common.shiro.principal.UserPrincipal;
import com.sitexa.facade.interfaces.UserService;
import io.jboot.core.rpc.annotation.JbootrpcService;
import io.jboot.web.controller.annotation.RequestMapping;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.Md5Hash;

@RequestMapping(value = "api")
public class LoginController extends BaseController {

    @JbootrpcService
    private UserService userService;


    public void index() {
        renderJson(Ret.relogin("请登录"));
    }

    public void login() {

        UserPrincipal users = getBeanByJsonParam(UserPrincipal.class);
        if (users == null) {
            renderJson(Ret.fail("用户名或密码不正确"));
            return;
        }
        String username = users.getMobilePhone();
        String password = users.getPassword();

        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            renderJson(Ret.relogin("账号或密码不能为空!"));
            return;
        }
        if (!validateCaptcha("captcha")) {
            renderJson(Ret.fail("验证码错误"));
            return;
        }
        Response response = null;
        try {
            response = userService.queryUsersByUsersName(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (response == null || response.fail()) {
            renderJson(Ret.fail("登录失败，请稍后再试"));
            return;
        }
        UserPrincipal dbUser = response.bean(UserPrincipal.class);
        if (dbUser == null) {
            renderJson(Ret.fail("你的用户名或密码错误"));
            return;
        }
        if (dbUser.getStatus() == UserState.disable.getState()) {
            renderJson(Ret.fail("账户已被禁用"));
            return;
        }
        if (dbUser.getStatus() == UserState.locked.getState()) {
            renderJson(Ret.fail("账户已被锁定"));
            return;
        }

        String verfyPassword = new Md5Hash(password, dbUser.getSalt(), 3).toString();
        if (!dbUser.getPassword().equals(verfyPassword)) {// 验证通过 生成token
            renderJson(Ret.fail("你的用户名或密码错误"));
            return;
        }
        UserVo userVo = new UserVo();
        userVo.copy(response);
        setJwtAttr("user", userVo);
        ShiroCacheUtils.getCacheManager().getCache(CacheKey.CACHE_JWT_TOKEN).remove(dbUser.getId() + "");

//		renderJson(Ret.ok("认证成功", usersVo));
        result(Ret.resultFile(userVo, BaseImgUrl.appUrl + BaseImgUrl.IMG_BASE));
    }


    @Clear
    public void captcha() {
        renderCaptcha();
    }


    public void logout() {
        String userId = getHeader("userId");
        if (userId == null) {
            renderJson(Ret.fail("参数有误"));
            return;
        }
        ShiroCacheUtils.getCacheManager().getCache(CacheKey.CACHE_JWT_TOKEN).remove(userId);
        renderJson(Ret.ok("退出成功"));
    }
}
