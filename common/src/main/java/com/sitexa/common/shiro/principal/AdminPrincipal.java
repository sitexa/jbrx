package com.sitexa.common.shiro.principal;

import java.io.Serializable;

public class AdminPrincipal implements Serializable{
	private static final long serialVersionUID = -1071298478680735026L;
	private Long id;   	//管理员ID
	private Long plateformId;//商家ID  ID为0则为管理员，1以上为商家
	private String userName;  //登录名 统一使用员工的手机号码
	private String avatar;  //头像图片
	private String password;  //登录密码
	private String salt;//盐
	private int status;  	//状态：1可用  2已禁用 3已删除
	private String loginTime;  	//登录时间
	private String createTime;  	//创建时间
	private String updateTime;	//修改时间

	public AdminPrincipal() {
	}

	public AdminPrincipal(Long id, Long plateformId, String userName, String avatar, String password, String salt, int status, String loginTime, String createTime, String updateTime) {
		this.id = id;
		this.plateformId = plateformId;
		this.userName = userName;
		this.avatar = avatar;
		this.password = password;
		this.salt = salt;
		this.status = status;
		this.loginTime = loginTime;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPlateformId() {
		return plateformId;
	}

	public void setPlateformId(Long plateformId) {
		this.plateformId = plateformId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
}
