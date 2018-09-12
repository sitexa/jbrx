package com.www.mall.common.constans;

import com.www.mall.common.bean.ESInfo;
import com.www.mall.common.bean.SSOInfo;

import io.jboot.Jboot;

public class Constans {
	//------------------------------------------------------------------------------
	/**
     * 首次请求
     */
	public static final Integer FIRST_REQUEST=1;
    /**
     * 加载更多
     */
	public static final Integer LOAD_MORE=2;

	/**
	 * 利万 APP登录 认证token
	 */
	public static final  String LW_LOGIN_TOKEN="lw_login_token";

	/**
	 * 利万 注册 平台ID
	 */
	public static final  String LW_REGISTER_PLATID="lw_register_platid";

	/**
	 * 利万 域名接口
	 */
	public static final  String LW_HTTP_URL="http://api.gglhkhk.hk:8090/";
	//------------------------------------------------------------------------------	
	
	public static final SSOInfo SSO;
	public static final ESInfo ES;
	static{
		SSO=Jboot.config(SSOInfo.class);
		ES=Jboot.config(ESInfo.class);
	}
	
}
