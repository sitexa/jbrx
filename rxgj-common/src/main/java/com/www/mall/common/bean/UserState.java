package com.www.mall.common.bean;

/**
 * ------------------------------
 * 状态：1可用  2已禁用 3已锁定 4已离职
 * ------------------------------
 * @author wdm  @date 2017年12月3日
 * @version 1.0
 */
public enum UserState{
	unknown(0,"未知错误"),
	enable(1,""),
	disable(2,"用户已被禁用!"),
	locked(3,"用户已被锁定!"),
	leave(4,"用户已被离职!");
	
	private int state;
	private String desc;
	private UserState(int state,String desc){
		this.state=state;
		this.desc=desc;
	}
	public int getState(){
		return state;
	}
	public String getDesc(){
		return desc;
	}
}
