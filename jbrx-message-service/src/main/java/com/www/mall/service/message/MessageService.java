package com.www.mall.service.message;

import javax.inject.Singleton;

import com.gavin.business.DBTrans;
import com.gavin.model.Request;
import com.gavin.model.Response;
import com.www.mall.common.bean.Ret;
import com.www.mall.message.dto.MessageRecord;
import com.www.mall.service.message.api.SmsUtils;

import io.jboot.aop.annotation.Bean;
import io.jboot.core.rpc.annotation.JbootrpcService;

@Bean
@Singleton
@JbootrpcService
public class MessageService implements com.www.mall.message.interf.MessageService{

	@Override
	public Ret sendCode(MessageRecord messageRecord) {
		//1.新增短信记录
		Request request=Request.build("MessageService", "insertMessageRecord").currentTime().from(messageRecord);
		Response response=DBTrans.execute(request);
		if(response.fail()){
			return Ret.result(response.result, response.message);
		}
		Ret ret=SmsUtils.sendCode(messageRecord.getReceiver(),messageRecord.getServiceType());
		if(ret.success()){//2.更新三方短信结果
			Request updateRequest=Request.build("MessageService", "updateMessageRecord").currentTime()
					.set("thirdSn", ret.getData()+"")
					.set("messageSn", messageRecord.getMessageSn());
			DBTrans.execute(updateRequest);
		}
		return ret;
	}

	@Override
	public Ret verifyCode(String messageId, String verifyCode) {
		Ret ret=SmsUtils.virifyCode(messageId, verifyCode);
		return ret;
	}
	
}
