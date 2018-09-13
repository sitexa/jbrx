package com.www.mall.service.user;

import com.gavin.model.Response;
import com.www.mall.common.base.BaseImgUrl;
import com.www.mall.common.bean.Ret;
import com.www.mall.common.bean.UserState;
import com.www.mall.common.bean.UsersVo;
import com.www.mall.common.shiro.cache.CacheKey;
import com.www.mall.common.shiro.cache.ShiroCacheUtils;
import com.www.mall.common.shiro.principal.User;
import com.xiaoleilu.hutool.util.RandomUtil;
import conf.BaseTest;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UsersServiceTest extends BaseTest {

    UsersService service;

    public UsersServiceTest() {
        service = new UsersService();
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void queryUsersByUsersName() {
    }

    @Test
    public void saveUsers() {
        String password = "xnpeng";
        String salt = RandomUtil.simpleUUID();
        String saltPassword = new Md5Hash(password, salt, 3).toString();
        User users = new User();
        users.setMobilePhone("18673107430");
        users.setPassword(saltPassword);
        users.setSalt(salt);
        users.setNickName("nanfang");
        Response response = service.saveUsers(users);
        result(response);
    }

    @Test
    public void queryUserNameExist() {
    }

    @Test
    public void queryUsersById() {
    }

    @Test
    public void updatePassword() {
    }

    @Test
    public void testLogin() {
        String mobile = "18673107430";
        String password = "xnpeng";

        Response response = null;
        try {
            response = service.queryUsersByUsersName(mobile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (response == null || response.fail()) {
            result(Ret.fail("登录失败，请稍后再试"));
            return;
        }
        User dbUsers = response.bean(User.class);
        if (dbUsers == null) {
            result(Ret.fail("你的用户名或密码错误"));
            return;
        }
        if (dbUsers.getStatus() == UserState.disable.getState()) {
            result(Ret.fail("账户已被禁用"));
            return;
        }
        if (dbUsers.getStatus() == UserState.locked.getState()) {
            result(Ret.fail("账户已被锁定"));
            return;
        }

        String verfyPassword = new Md5Hash(password, dbUsers.getSalt(), 3).toString();
        if (!dbUsers.getPassword().equals(verfyPassword)) {// 验证通过 生成token
            result(Ret.fail("你的用户名或密码错误"));
            return;
        }
        UsersVo usersVo = new UsersVo();
        usersVo.copy(response);

        //setJwtAttr("user", usersVo);
        //ShiroCacheUtils.getCacheManager().getCache(CacheKey.CACHE_JWT_TOKEN).remove(dbUsers.getId() + "");

//		renderJson(Ret.ok("认证成功", usersVo));
        result(Ret.resultFile(usersVo, BaseImgUrl.fastUrl + BaseImgUrl.IMG_BASE));
    }
}