package com.www.mall.controller.api;

import com.www.mall.common.base.BaseImgUrl;
import com.www.mall.common.shiro.principal.UserPrincipal;
import com.www.mall.user.interf.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import com.gavin.model.Response;
import com.jfinal.aop.Clear;
import com.www.mall.common.base.BaseController;
import com.www.mall.common.bean.Ret;
import com.www.mall.common.bean.UserState;
import com.www.mall.common.bean.UserVo;
import com.www.mall.common.shiro.cache.CacheKey;
import com.www.mall.common.shiro.cache.ShiroCacheUtils;

import io.jboot.core.rpc.annotation.JbootrpcService;
import io.jboot.web.controller.annotation.RequestMapping;

/**
 * ------------------------------ 管理员控制器 ------------------------------
 * 
 * @author wdm @date 2017年11月27日
 * @version 1.0
 */
@RequestMapping(value = "app")
public class LoginController extends BaseController {

	@JbootrpcService
	private UserService userService;

	/**
	 * 登录后的主页面
	 * 
	 * @author wdm @date 2017年11月29日
	 * @version 1.0
	 */
	public void index() {
		renderJson(Ret.relogin("请登录"));
	}

	/**
	 * 登录
	 * 
	 * @author wdm @date 2017年11月29日
	 * @version 1.0
	 */
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
		// if (!validateCaptcha("captcha")) {
		// renderMessage("验证码错误!");
		// return;
		// }
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
		UserPrincipal dbUsers = response.bean(UserPrincipal.class);
		if (dbUsers == null) {
			renderJson(Ret.fail("你的用户名或密码错误"));
			return;
		}
		if (dbUsers.getStatus() == UserState.disable.getState()) {
			renderJson(Ret.fail("账户已被禁用"));
			return;
		}
		if (dbUsers.getStatus() == UserState.locked.getState()) {
			renderJson(Ret.fail("账户已被锁定"));
			return;
		}

		String verfyPassword = new Md5Hash(password, dbUsers.getSalt(), 3).toString();
		if (!dbUsers.getPassword().equals(verfyPassword)) {// 验证通过 生成token
			renderJson(Ret.fail("你的用户名或密码错误"));
			return;
		}
		UserVo usersVo = new UserVo();
		usersVo.copy(response);

		setJwtAttr("user", usersVo);
		ShiroCacheUtils.getCacheManager().getCache(CacheKey.CACHE_JWT_TOKEN).remove(dbUsers.getId() + "");

//		renderJson(Ret.ok("认证成功", usersVo));
		result(Ret.resultFile(usersVo,BaseImgUrl.fastUrl+BaseImgUrl.IMG_BASE));
	}

	/**
	 * 验证码
	 */
	@Clear
	public void captcha() {
		renderCaptcha();
	}

	/**
	 * 退出系统
	 * 
	 * @author wdm @date 2017年11月29日
	 * @version 1.0
	 */
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
