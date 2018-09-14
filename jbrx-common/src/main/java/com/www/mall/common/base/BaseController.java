package com.www.mall.common.base;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gavin.model.Response;
import com.jfinal.log.Log;
import com.www.mall.common.bean.*;
import com.www.mall.common.bean.UserVo;
import com.www.mall.common.shiro.ShiroUtils;
import com.www.mall.common.utils.BlankUtil;
import com.www.mall.common.utils.ParamUtil;

import io.jboot.web.controller.JbootController;
import io.jboot.web.cors.EnableCORS;

/**
 * ------------------------------
 * 控制器基类
 * ------------------------------
 * @author wdm（l311576@sina.com）  @date 2018年6月5日
 * @version 1.0
 */
@EnableCORS(exposeHeaders="Jwt",allowHeaders="Jwt")
public class BaseController extends JbootController {
	static Log log=Log.getLog(BaseController.class);
	static{
//		TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
	}
	
	public final static String PARAM_NAME="param";
	public final static String HEADER_PLOY_NAME="ploy";
	
	/**
	 * 获取参数名为param的json参数 并转化为bean
	 * @param clazz
	 * @return
	 */
	protected <T> T getBeanByJsonParam(Class<T> clazz) {
		String json=getPara(PARAM_NAME);
		if(json==null){
			return null;
		}
		String ploy=getHeader(HEADER_PLOY_NAME);
		if(Ploy.ENCRYPT.equals(ploy)){//参数解密
			json=ParamUtil.decryptParam(json);
		}
		try {
			return JSON.parseObject(json, clazz);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	protected String getParam(String feild,String defaultValue){
		String json=getPara(PARAM_NAME);
		if(json==null){
			return defaultValue;
		}
		String ploy=getHeader(HEADER_PLOY_NAME);
		if(Ploy.ENCRYPT.equals(ploy)){//参数解密
			json=ParamUtil.decryptParam(json);
		}
		try {
			JSONObject jobj=JSON.parseObject(json);
			if(jobj.getString(feild)==null){
				return defaultValue;
			}
			return jobj.getString(feild);
		} catch (Exception e) {
			e.printStackTrace();
			return defaultValue;
		}
	}
	
	protected Integer getParamInteger(String feild){
		return getParamInteger(feild,null);
	}
	
	protected Long getParamLong(String feild){
		return getParamLong(feild,null);
	}
	
	protected long getParamToLong(String feild){
		return getParamToLong(feild,-1);
	}
	
	protected String getParam(String feild){
		return getParam(feild,null);
	}
	
	protected int getParamToInt(String feild){
		return getParamToInt(feild,-1);
	}
	
	protected int getParamToInt(String feild,int defaultValue){
		String json=getPara(PARAM_NAME);
		if(json==null){
			return defaultValue;
		}
		String ploy=getHeader(HEADER_PLOY_NAME);
		if(Ploy.ENCRYPT.equals(ploy)){//参数解密
			json=ParamUtil.decryptParam(json);
		}
		try {
			JSONObject jobj=JSON.parseObject(json);
			if(jobj.getString(feild)==null){
				return defaultValue;
			}
			return Integer.valueOf(jobj.getString(feild));
		} catch (Exception e) {
			e.printStackTrace();
			return defaultValue;
		}
	}
	
	protected long getParamToLong(String feild,long defaultValue){
		String json=getPara(PARAM_NAME);
		if(json==null){
			return defaultValue;
		}
		String ploy=getHeader(HEADER_PLOY_NAME);
		if(Ploy.ENCRYPT.equals(ploy)){//参数解密
			json=ParamUtil.decryptParam(json);
		}
		try {
			JSONObject jobj=JSON.parseObject(json);
			if(jobj.getString(feild)==null){
				return defaultValue;
			}
			return Long.valueOf(jobj.getString(feild));
		} catch (Exception e) {
			e.printStackTrace();
			return defaultValue;
		}
	}
	
	protected Integer getParamInteger(String feild,Integer defaultValue){
		String json=getPara(PARAM_NAME);
		if(json==null){
			return defaultValue;
		}
		String ploy=getHeader(HEADER_PLOY_NAME);
		if(Ploy.ENCRYPT.equals(ploy)){//参数解密
			json=ParamUtil.decryptParam(json);
		}
		try {
			JSONObject jobj=JSON.parseObject(json);
			if(jobj.getInteger(feild)==null){
				return defaultValue;
			}
			return jobj.getInteger(feild);
		} catch (Exception e) {
			e.printStackTrace();
			return defaultValue;
		}
	}
	
	protected Long getParamLong(String feild,Long defaultValue){
		String json=getPara(PARAM_NAME);
		if(json==null){
			return defaultValue;
		}
		String ploy=getHeader(HEADER_PLOY_NAME);
		if(Ploy.ENCRYPT.equals(ploy)){//参数解密
			json=ParamUtil.decryptParam(json);
		}
		try {
			JSONObject jobj=JSON.parseObject(json);
			if(jobj.getLong(feild)==null){
				return defaultValue;
			}
			return jobj.getLong(feild);
		} catch (Exception e) {
			e.printStackTrace();
			return defaultValue;
		}
	}

	
	
	
	/**
	 * 从body中获取json参数 并转化为bean
	 * @param clazz
	 * @return
	 */
	protected <T> T getBeanByJsonBody(Class<T> clazz) {
		String ploy=getHeader(HEADER_PLOY_NAME);
		String json=getBodyString();
		if(json==null){
			return null;
		}
		if(Ploy.ENCRYPT.equals(ploy)){//参数解密
			json=ParamUtil.decryptParam(json);
		}
		try {
			return JSON.parseObject(json, clazz);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	/**分页大小**/
	protected int pageSize(){
		return getParamToInt("pageSize",10);
	}
	/**分页页码**/
	protected int pageNumber(){
		return getParamToInt("pageNumber",1)-1;
	}
		
	/**
	 * 响应json数据
	 * @param data
	 */
	protected void result(Object data){
		if(data==null){
			renderJson(Ret.fail("请求失败或数据为空"));
		}else{
			String ret=Ret.ok("OK", data).toString();
			log.info("响应结果:"+ret);
			renderJson(ret);
		}
	}
	
	
	/**
	 * 响应json
	 * result
	 * message
	 * @param result
	 * @param message
	 */
	protected void result(RC result,String message){
		String ret=Ret.result(result.getState(), message).toString();
		log.info("响应结果:"+ret);
		renderJson(ret);
	}
	
	/**
	 * 响应json
	 * @param result
	 */
	protected void result(Response result) {
		if(result==null){
			renderJson(Ret.result(RC.BUSINESS_FAIL.getState(), "请求失败"));
		}else{
			String ret=Ret.result(result.getResult(), result.getMessage(),result.getData()).toString();
			log.info("响应结果:"+ret);
			renderJson(ret);
		}
	}
	
	/**
	 * 响应json
	 * @param result
	 */
	protected void result(Ret ret) {
		if(ret==null){
			renderJson(Ret.result(RC.BUSINESS_FAIL.getState(), "请求失败"));
		}else{
			log.info("响应结果:"+ret);
			renderJson(ret);
		}
	}
	
	/**
	 * 响应json
	 * @param result
	 */
	protected void result(int state, String systemException) {
		Ret ret=Ret.result(state, systemException);
		log.info("响应结果:"+ret);
		renderJson(ret);
	}
	
	/**
	 * 响应json
	 * @param result
	 */
	protected void result(int state, String msg, Object response) {
		Ret ret=Ret.result(state, msg,response);
		log.info("响应结果:"+ret);
		renderJson(ret);
	}
	
	/**
	 * 获取参数名为param的json参数 并转化为list bean
	 * @param <T>
	 * @param <T>
	 * @param clazz
	 * @return
	 */
	protected <T> List<T> getBeanByList(Class<T> clazz) {
		String json=getPara(PARAM_NAME);
		if(json==null){
			return null;
		}
		String ploy=getHeader(HEADER_PLOY_NAME);
		if(Ploy.ENCRYPT.equals(ploy)){//参数解密
			json=ParamUtil.decryptParam(json);
		}
		List<T> list = (List<T>) JSON.parseArray(json,clazz);
		return list;
	}	
	
	/**
	 * 是否认证
	 * @return
	 */
	public boolean isAuth(){
		Object principal=ShiroUtils.getSubject().getPrincipal();
		return principal!=null;
	}
	
	/**
	 * 获取用户id
	 * @return
	 */
	public Long getUserId(){
		Object principal=ShiroUtils.getSubject().getPrincipal();
		if(BlankUtil.isBlank(principal)){
			return null;
		}else {
			try {
				return Long.valueOf(principal+"");
			}catch (Exception e) {
				return null;
			}
			
		}
	}
	/**
	 * 获取系统管理用户id
	 */
	public Long getAdminsId(){
		Long adminsId=getJwtPara("adminsId");
		if(adminsId==null){
			return null;
		}
		return adminsId;

//		Object principal=ShiroUtils.getSubject().getPrincipal();
//		if(BlankUtil.isBlank(principal)){
//			return null;
//		}else {
//			try {
//				return Long.valueOf(principal+"");
//			}catch (Exception e) {
//				return null;
//			}
//
//		}
	}
	/**
	 * 获取系统管理用户
	 */
	public AdminVo getAdmins(){
		Map<String,Object> adminsHm=getJwtPara("admins");
		if(adminsHm==null){
			return null;
		}
		AdminVo admins=new AdminVo();
		for (Entry<String, Object> entry :adminsHm.entrySet()) {
			Object value=entry.getValue();
			if(value instanceof String){
				admins.set(entry.getKey(), (String)value);
			}else if(value instanceof Long){
				admins.set(entry.getKey(), (Long)value);
			}else if(value instanceof Integer){
				admins.set(entry.getKey(), (Integer)value);
			}else{
				admins.set(entry.getKey(), value+"");
			}
		}
		log.info("****************************************** 当前登录用户信息为: **********************************************\n");
		log.info(admins.toJson()+"\n");
		log.info("*********************************************************************************************************");

		return admins;
	}
	/**
	 * 获取用户信息
	 * @return
	 */
	public UserVo getUser(){
		Map<String,Object> userHm=getJwtPara("user");
		if(userHm==null){
			return null;
		}
		UserVo user=new UserVo();
		for (Entry<String, Object> entry :userHm.entrySet()) {
			Object value=entry.getValue();
			if(value instanceof String){
				user.set(entry.getKey(), (String)value);
			}else if(value instanceof Long){
				user.set(entry.getKey(), (Long)value);
			}else if(value instanceof Integer){
				user.set(entry.getKey(), (Integer)value);
			}else{
				user.set(entry.getKey(), value+"");
			}
		}
		log.info("****************************************** 当前登录用户信息为: **********************************************\n");
		log.info(user.toJson()+"\n");
		log.info("*********************************************************************************************************");
		
		return user;
	}
	
//--非前后端分离--------------------------------------------------------------------------------------------------------------------------------------
//	/**分页大小 默认10条**/
//	protected int pageSize(){
//		return getParaToInt("pageSize", 10);
//	}
//	/**分页页码 默认从0开始**/
//	protected int pageNumber(){
//		return getParaToInt("pageNumber", 0);
//	}	
//	/**
//	 * 响应结果到页面
//	 * result
//	 * message
//	 * @param result
//	 * @param message
//	 */
//	protected void result(RC result,String message){
//		setFlashAttr("message", message);
//		setFlashAttr("result", result.getState());
//	}
//	
//	/**
//	 * 响应结果到页面
//	 * @param result
//	 */
//	protected void result(Response result) {
//		if(result==null){
//			setFlashAttr("message", "请求失败");
//			setFlashAttr("result", RC.BUSINESS_FAIL);
//		}else{
//			setFlashAttr("message", result.getMessage());
//			setFlashAttr("result", result.getResult());
//		}
//	}
}

