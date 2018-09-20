package com.sitexa.msg.vo;

import java.io.Serializable;

public class Message implements Serializable{
	private static final long serialVersionUID = -8099489858096931258L;
	
	private  long id;
	private  int messageType = 0;//消息类型：1 sms（短信） 2 email（邮件） 3 push（推送消息）
	private  String channel = ""; // 通道账户：发送者
	private  String receiver = "";//接收者email或手机号码
	private  String title = "";//標題
	private  long templateId; //模板id
	private  String templateParam;	 //模板参数
	private  int weight = 0; //权重
	private  long failCount = 0;//发送失败次数
	private  long status = 0;//0：未发送 1：发送中 2：发送成功 3：发送失败  4：发送成功
	private  long recoveryStatus = 0;//0：待回收
	private  String scheduleTime;//预约发送时间
	private  String doneTime; //完成时间
	private  String createTime; //创建时间
	private  String updateTime; //修改时间
	
	
	public  long  getId(){
		return  this.id;
	}
	public  void  setId(long id){
		this.id=id;
	}  
	
	public  int  getMessageType(){
		return  this.messageType;
	}
	public  void  setMessageType(int messageType){
		this.messageType=messageType;
	}  
	
	public  String  getChannel(){
		return  this.channel;
	}
	public  void  setChannel(String channel){
		this.channel=channel;
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
	
	public  long  getTemplateId(){
		return  this.templateId;
	}
	public  void  setTemplateId(long templateId){
		this.templateId=templateId;
	}  
	
	public  String  getTemplateParam(){
		return  this.templateParam;
	}
	public  void  setTemplateParam(String templateParam){
		this.templateParam=templateParam;
	}  
	
	public  int  getWeight(){
		return  this.weight;
	}
	public  void  setWeight(int weight){
		this.weight=weight;
	}  
	
	public  long  getFailCount(){
		return  this.failCount;
	}
	public  void  setFailCount(long failCount){
		this.failCount=failCount;
	}  
	
	public  long  getStatus(){
		return  this.status;
	}
	public  void  setStatus(long status){
		this.status=status;
	}  
	
	public  long  getRecoveryStatus(){
		return  this.recoveryStatus;
	}
	public  void  setRecoveryStatus(long recoveryStatus){
		this.recoveryStatus=recoveryStatus;
	}  
	
	public  String  getScheduleTime(){
		return  this.scheduleTime;
	}
	public  void  setScheduleTime(String scheduleTime){
		this.scheduleTime=scheduleTime;
	}  
	
	public  String  getDoneTime(){
		return  this.doneTime;
	}
	public  void  setDoneTime(String doneTime){
		this.doneTime=doneTime;
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
     
    
}