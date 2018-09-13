package com.www.mall.common.bean;

import java.io.Serializable;
import java.util.Date;

import com.gavin.model.Model;
import io.swagger.models.auth.In;

/**
 * ------------------------------
 * Automatic Generator
 * ------------------------------
 * @author wdm  @date 2018年01月26日
 * @version 1.0
 */
public class UsersVo extends Model implements Serializable{

	private static final long serialVersionUID = -758439056156155606L;

	public UsersVo(){}
	
	 
	public void setId(Long id) {
		set("id", id);
	}
	
	public Long getId() {
		return getLong("id");
	}

	public void setPic(String pic){
		set("pic", pic);
	}
	public String getPic(){
		return getString("pic");
	}
	public void setNickName(String nickName){
		set("nickName", nickName);
	}
	public String getNickName(){
		return getString("nickName");
	}
	public void setRealName(String realName){
		set("realName", realName);
	}
	public String getRealName(){
		return getString("realName");
	}
	public void setSex(Integer sex){
		set("sex", sex);
	}
	public Integer getSex(){
		return getInteger("sex");
	}
	public void setMobilePhone(String mobilePhone){
		set("mobilePhone", mobilePhone);
	}
	public String getMobilePhone(){
		return getString("mobilePhone");
	}
	public void setPassword(String password){
		set("password", password);
	}
	public String getPassword(){
		return getString("password");
	}
	public void setSalt(String salt){
		set("salt", salt);
	}
	public String getSalt(){
		return getString("salt");
	}
	public void setFailCount(Integer failCount){
		set("failCount", failCount);
	}
	public Integer getFailCount(){
		return getInteger("failCount");
	}
	public void setLockTime(Date lockTime){
		set("lockTime",lockTime );
	}
	public String getLockTime(){
		return getDate("lockTime");
	}
}
