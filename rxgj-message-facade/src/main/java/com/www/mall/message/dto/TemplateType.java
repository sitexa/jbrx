package com.www.mall.message.dto;

public enum TemplateType {
	/**注册**/
	REGISTER(145827),
	/**修改密码**/
	UPDATE_PWD(145631),
	/**设置支付密码**/
	PAY_PWD_SET(145851),	
	/**修改支付密码**/
	PAY_PWD_UPDATE(145852),
	/**同意退货**/
	AGREE_RETURN_GOODS(148259),
	/**拒绝退货**/
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
	
	
	public static TemplateType getTemplateType(String serviceType){
		TemplateType[] templateTypes=TemplateType.values();
		for (int i = 0; i < templateTypes.length; i++) {
			if(templateTypes[i].name().equals(serviceType)){
				return templateTypes[i];
			}
		}
		return null;
	}
}
