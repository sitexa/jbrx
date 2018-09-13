package com.www.mall.message.dto;

public class Sms {

	private String msgId;

	public Sms() {
		super();
	}
	
	public Sms(String msgId){
		this.msgId=msgId;
	}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

}
