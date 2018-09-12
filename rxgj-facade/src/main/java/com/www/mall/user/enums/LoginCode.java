package com.www.mall.user.enums;

public enum LoginCode {
	SUCCESS(1),
	PWD_FAIL(2),
	NOT_EXIST(3);
	
	private int code;
	LoginCode(int code){
		this.code=code;
	}
	
	public int code(){
		return code;
	}
}
