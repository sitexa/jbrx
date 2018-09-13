package com.www.mall.message.interf;

import com.www.mall.common.bean.Ret;
import com.www.mall.message.dto.MessageRecord;

public interface MessageService {

	/**
	 * 发送短信
	 * @param mobilePhone
	 * @return
	 */
	public Ret sendCode(MessageRecord messageRecord);
	
	/**
	 * 验证短信
	 * @param mobilePhone
	 * @param messageId
	 * @param code
	 * @return
	 */
	public Ret verifyCode(String messageId, String code);
	
}
