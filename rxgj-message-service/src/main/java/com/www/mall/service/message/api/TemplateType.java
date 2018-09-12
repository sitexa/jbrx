package com.www.mall.service.message.api;

public enum TemplateType {
	REGISTER(145827),
	UPDATE_PWD(145631),
	PAY_PWD_SET(145851),	
	PAY_PWD_UPDATE(145852),
	AGREE_RETURN_GOODS(148259),
	REJECT_RETURN_GOODS(148263);
	
	private int templateTypeWithJPush;
	
	TemplateType(int templateTypeWithJPush){
		this.templateTypeWithJPush=templateTypeWithJPush;
	}
	
	/**
	 * 返回极光短信模板id
	 * @return
	 */
	public int templateTypeWithJPush(){
		return templateTypeWithJPush;
	}
}
