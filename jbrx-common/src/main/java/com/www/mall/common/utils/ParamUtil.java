package com.www.mall.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.www.mall.common.encrypt.BackAES;

public class ParamUtil {
	private static Logger logger=LoggerFactory.getLogger(ParamUtil.class);
	
	public static final String param_aes_encrypt_key="SL#)@F&9)0Gsf090";

	public static String decryptParam(String param) {
		String realParam;
		try {
			realParam = BackAES.decrypt(param,param_aes_encrypt_key, 0);
			logger.info("param:"+param+"--->"+realParam);
			return realParam;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
