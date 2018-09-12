package com.www.mall.controller;

import com.www.mall.user.interf.AdminsService;
import io.jboot.Jboot;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.Md5Hash;

import com.jfinal.aop.Clear;
import com.www.mall.common.base.BaseController;
import com.www.mall.common.bean.Ret;
import com.www.mall.common.bean.UserState;
import com.www.mall.common.shiro.cache.CacheKey;
import com.www.mall.common.shiro.cache.ShiroCacheUtils;
import com.www.mall.common.shiro.principal.Admins;
import io.jboot.core.rpc.annotation.JbootrpcService;
import io.jboot.web.controller.annotation.RequestMapping;

/**
 * ------------------------------
 * 管理员控制器
 * ------------------------------
 * @author wdm  @date 2017年11月27日
 * @version 1.0
 */
@RequestMapping(value =  "/admin/login")
public class LoginController extends BaseController{
	
	@JbootrpcService
	private AdminsService adminsService;
	/**
	 * 登录后的主页面
	 * @author wdm  @date 2017年11月29日
	 * @version 1.0
	 */
	public void index() {
		renderJson(Ret.relogin("请登录"));
	}
	
	/**
	 * 登录
	 * @author wdm  @date 2017年11月29日
	 * @version 1.0
	 */
	public void login() {

		Admins adminDb=getBeanByJsonParam(Admins.class);
		if(adminDb==null){
			renderJson(Ret.fail("用户名或密码不正确"));
			return;
		}
		String username = adminDb.getUserName();
		String password = adminDb.getPassword();

		if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
			renderJson(Ret.relogin("账号或密码不能为空!"));
			return;
		}
		Admins admins=null;
		try {
			AdminsService adminsService=Jboot.service(AdminsService.class);
			admins=adminsService.getAdminsByUserName(username);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (admins == null) {
			result(Ret.fail(UserState.unknown.getDesc()));
			return;
		}

		if(admins.getStatus()==UserState.disable.getState()) {
			result(Ret.fail(UserState.disable.getDesc()));
			return;
		}
		if(admins.getStatus()==UserState.locked.getState()) {
			result(Ret.fail(UserState.locked.getDesc()));
			return;
		}

		String verfyPassword=new Md5Hash(password,admins.getSalt(),3).toString();
		if(!admins.getPassword().equals(verfyPassword)){//验证通过 生成token
			result(Ret.fail("用户名或密码不正确"));
			return;
		}
		setJwtAttr("adminsId", admins.getId());
		setJwtAttr("admins", admins);
		ShiroCacheUtils.getCacheManager().getCache(CacheKey.CACHE_JWT_TOKEN).remove(admins.getId()+"");
		renderJson(Ret.ok("认证成功",admins));

	}

	
	public void center(){
//		String userId=getHeader("userId");
//		ShiroCacheUtils.getCacheManager().getCache(CacheKey.CACHE_JWT_TOKEN).get(userId);
//		List<Menu> result=menuService.queryUserMenu(Long.valueOf(userId));
//		if(result==null){
//			renderJson(Ret.fail("查询菜单信息异常"));
//		}else{
////			List<TreeModel> list=TreeUtil.instance().buildMenuTree(result);
////			renderJson(Ret.result(RC.SUCCESS, "OK",list));
//		}
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
	 * @author wdm  @date 2017年11月29日
	 * @version 1.0
	 */
	public void logout(){
		//getHeader("userId")
		Long userId=getAdminsId();
		if(userId==null){
			renderJson(Ret.fail("参数有误"));
			return ;
		}
		ShiroCacheUtils.getCacheManager().getCache(CacheKey.CACHE_JWT_TOKEN_OVERDUE).put(userId+"", true);
		renderJson(Ret.ok("退出成功"));
	}
	
}
