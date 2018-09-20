package com.sitexa.msg.vo;

import com.xiaoleilu.hutool.date.DatePattern;
import com.xiaoleilu.hutool.date.DateUtil;

import java.io.Serializable;
import java.util.Date;

public class MessageRecord implements Serializable{
     
	private static final long serialVersionUID = 283063888170565918L;
	private String messageSn;
	private  int messageType; //消息类型：1 sms 2 email 3 push
	private  String serviceType;// 业务类型：注册、提醒发货...
	private  String receiver; //接收者email或手机号码
	private  String title; //標題
	private  String content = "";//内容
	private  int status; //1待发送 2待验证 3已完成
	private String messageDate=DateUtil.format(new Date(), DatePattern.NORM_DATE_PATTERN);
	private String thirdSn;
	private  String sendTime; //完成时间
	private  String verifyTime;//验证时间
	private  String createTime; //创建时间
	private  String updateTime; //修改时间
	
	
	public  int  getMessageType(){
		return  this.messageType;
	}
	public  void  setMessageType(int messageType){
		this.messageType=messageType;
	}  
	
	public  String  getServiceType(){
		return  this.serviceType;
	}
	public  void  setServiceType(String serviceType){
		this.serviceType=serviceType;
	}  
	
	public  String  getReceiver(){
		return  this.receiver;
	}
	public  void  setReceiver(String receiver){
		this.receiver=receiver;
	}  
	
	public  String  getTitle(){
		return  this.title;
	}
	public  void  setTitle(String title){
		this.title=title;
	}  
	
	public  String  getContent(){
		return  this.content;
	}
	public  void  setContent(String content){
		this.content=content;
	}  
	
	public  int  getStatus(){
		return  this.status;
	}
	public  void  setStatus(int status){
		this.status=status;
	}  
	public String getSendTime() {
		return sendTime;
	}
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	public  String  getVerifyTime(){
		return  this.verifyTime;
	}
	public  void  setVerifyTime(String verifyTime){
		this.verifyTime=verifyTime;
	}  
	public  String  getCreateTime(){
		return  this.createTime;
	}
	public  void  setCreateTime(String createTime){
		this.createTime=createTime;
	}  
	public  String  getUpdateTime(){
		return  this.updateTime;
	}
	public  void  setUpdateTime(String updateTime){
		this.updateTime=updateTime;
	}
	public String getMessageDate() {
		return messageDate;
	}
	public void setMessageDate(String messageDate) {
		this.messageDate = messageDate;
	}
	public String getMessageSn() {
		return messageSn;
	}
	public String getThirdSn() {
		return thirdSn;
	}
	public void setMessageSn(String messageSn) {
		this.messageSn = messageSn;
	}
	public void setThirdSn(String thirdSn) {
		this.thirdSn = thirdSn;
	}

    
}