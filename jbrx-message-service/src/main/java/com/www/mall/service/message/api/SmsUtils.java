package com.www.mall.service.message.api;

import java.util.Map;

import com.www.mall.common.bean.Ret;
import com.www.mall.message.dto.TemplateType;

public class SmsUtils {
	public static ISms sms=new JPushSms();
	
	public static Ret sendCode(String mobilePhone,String serviceType){
		TemplateType templateType=TemplateType.getTemplateType(serviceType);
		if(templateType==null){
			return Ret.fail("不支持的业务类型");
		}
		return sms.sendCode(mobilePhone, "", templateType.templateTypeWithJPush());
	}
	
	public static Ret virifyCode(String messageId,String verifyCode){
		return sms.virifyCode(messageId, verifyCode);
	}
	
	public static Ret sendMessage(String mobilePhone,TemplateType template,Map<String,String> param){
		return sms.sendMessage(mobilePhone, template.templateTypeWithJPush() , param);
	}
}
