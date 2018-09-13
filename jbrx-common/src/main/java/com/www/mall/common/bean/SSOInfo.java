package com.www.mall.common.bean;

import io.jboot.config.annotation.PropertyConfig;

/**
 * ------------------------------
 * 单点登录信息
 * ------------------------------
 * @author wdm（l311576@sina.com）  @date 2018年6月2日
 * @version 1.0
 */
@PropertyConfig(prefix = "jboot.sso")
public class SSOInfo {

    private String sysId;//系统唯一标示
    private String appId;//boss系统颁发的appId
    private String serverUrl="http://sso.boss.liyou.com/login";//boss系统访问地址
    private String verifyTokenUrl="http://sso.boss.liyou.com/verifyToken";//boss系统token校验
    
	public String getSysId() {
		return sysId;
	}
	public void setSysId(String sysId) {
		this.sysId = sysId;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getServerUrl() {
		return serverUrl;
	}
	public void setServerUrl(String serverUrl) {
		this.serverUrl = serverUrl;
	}
	public String getVerifyTokenUrl() {
		return verifyTokenUrl;
	}
	public void setVerifyTokenUrl(String verifyTokenUrl) {
		this.verifyTokenUrl = verifyTokenUrl;
	}
 
}
