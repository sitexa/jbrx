package com.www.mall.service.message.api;

import com.jfinal.kit.PropKit;

public interface Config {
	
	//-----------------------------------------------极光配置start------------------------------------------------------
    public static String JPUSH_SMS_APPKEY = "31e4cbe1d36d11bc10baa2c2";
    public static String JPUSH_MASTER_SECRET = "df5edbaa1e67582f6f0ef72e";

    //-----------------------------------------------极光配置end------------------------------------------------------
	public static final String mode=PropKit.get("message.mode","pro");
	
}
