package com.sitexa.common.utils;

import com.sitexa.common.encrypt.BackAES;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
