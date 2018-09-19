package com.sitexa.common.validator;

/**
 * ------------------------------
 * 验证数据类型
 * ------------------------------
 */
public enum VType {
	/**非空验证**/
	notnull,
	/**非空时验证电子邮箱格式，空表示通过**/
	ne_email,
	/**非空时验证身份证格式，空表示通过**/	
	ne_idcard,
	
	/**验证电子邮箱格式**/
	email,
	/**验证身份证格式**/	
	idcard,
	/**验证日期格式**/	
	date,
	/***手机号验证***/
	mobilePhone,
	/***密码验证***/
	password,	
	/***数字验证***/
	number
}
