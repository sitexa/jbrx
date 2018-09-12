package com.www.mall.service.message.api;

import java.util.Map;

import com.www.mall.common.bean.Ret;

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
