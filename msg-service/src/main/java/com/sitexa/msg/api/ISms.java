package com.sitexa.msg.api;

import com.sitexa.common.bean.Ret;

import java.util.Map;

public interface ISms {
	
	/**
	 * 发送验证码
	 * @return
	 */
	public Ret sendCode(String mobilePhone, String code, int template);
	
	/**
	 * 发送短信
	 * @return
	 */
	public Ret sendMessage(String mobilePhone, int template, Map<String, String> param);
	
	/**
	 * 处理验证码
	 * @return
	 */
	public Ret virifyCode(String messageId, String verifyCode);
}
