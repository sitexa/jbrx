package com.www.mall.service.message.api;

import java.util.Map;
import org.apache.log4j.Logger;
import com.www.mall.common.bean.Ret;
import cn.jsms.api.SendSMSResult;
import cn.jsms.api.ValidSMSResult;
import cn.jsms.api.common.SMSClient;
import cn.jsms.api.common.model.SMSPayload;
/**
 * ------------------------------
 * 极光短信
 * ------------------------------
 * @author wdm（l311576@sina.com）  @date 2018年7月12日
 * @version 1.0
 */
public class JPushSms implements ISms{

	private static Logger logger=Logger.getLogger(JPushSms.class);
	
	@Override
	public Ret sendCode(String mobilePhone,String code, int template) {
		if("dev".equals(Config.mode)){
			Ret ret=Ret.ok("短息未发送，当前为开发模式");
			ret.setData("88888888");
			return ret;
		}
		Ret ret=Ret.fail("短息发送失败");
		try {
			logger.info("准备发送极光短信验证码");
			SMSPayload payload = SMSPayload.newBuilder()
					.setMobileNumber(mobilePhone)
//					.setCode(code)
					.setTempId(template).build();
			SMSClient client = new SMSClient(Config.JPUSH_MASTER_SECRET, Config.JPUSH_SMS_APPKEY);
			SendSMSResult smsResult = client.sendSMSCode(payload);
			logger.info("极光短信发送结果:"+smsResult);
			
			if(smsResult==null){
				return ret;
			}
			
			int  resultCode=smsResult.getResponseCode();
			String messageId = smsResult.getMessageId();
			if(smsResult.isResultOK()){
				return Ret.ok("发送成功", messageId);
			}else{
				return Ret.fail("发送失败("+resultCode+")");
			}
		} catch (Exception e) {
            logger.error("发送异常:"+e.getMessage(), e);
			return Ret.fail("发送异常");
		}
	}
	
	@Override
	public Ret sendMessage(String mobilePhone, int template, Map<String, String> param) {
		if("dev".equals(Config.mode)){
			Ret ret=Ret.ok("短息未发送，当前为开发模式");
			ret.setData("88888888");
			return ret;
		}
		Ret ret=Ret.fail("短息发送失败");
		try {
			logger.info("准备发送极光短信");
			SMSPayload payload = SMSPayload.newBuilder()
					.setMobileNumber(mobilePhone)
					.setTempId(template)
					.setTempPara(param).build();
			SMSClient client = new SMSClient(Config.JPUSH_MASTER_SECRET, Config.JPUSH_SMS_APPKEY);
			SendSMSResult smsResult = client.sendSMSCode(payload);
			
			logger.info("极光短信发送结果:"+smsResult);
			
			if(smsResult==null){
				return ret;
			}			
			int  resultCode=smsResult.getResponseCode();
			String messageId = smsResult.getMessageId();
			if(smsResult.isResultOK()){
				return Ret.ok("发送成功", messageId);
			}else{
				return Ret.fail("发送失败("+resultCode+")");
			}
		} catch (Exception e) {
            logger.error("发送异常:"+e.getMessage(), e);
			return Ret.fail("发送异常");
		}
	}

	@Override
	public Ret virifyCode(String messageId, String verifyCode) {
		if("dev".equals(Config.mode)){
			Ret ret=Ret.ok("短息未验证，当前为开发模式");
			ret.setData("88888888");
			return ret;
		}
		Ret ret=Ret.fail("验证短信失败");
		try {
			SMSClient client = new SMSClient(Config.JPUSH_MASTER_SECRET, Config.JPUSH_SMS_APPKEY);
			ValidSMSResult res = client.sendValidSMSCode(messageId, verifyCode);
			Boolean isValid = res.getIsValid();
			if (res.isResultOK() && isValid) {
				return Ret.ok("短信验证成功");
			}
		} catch (Exception e) {
			logger.error("短信验证异常:"+e.getMessage(), e);
			return Ret.fail("短信验证异常");
		}
		return ret;
	}
	
	
//	public static void main(String[] args) {
//		new JPushSms().sendCode("18514589891", "8886ssss66", 145827);
//	}


}
