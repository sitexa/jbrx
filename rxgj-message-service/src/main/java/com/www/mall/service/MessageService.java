package com.www.mall.service;

import com.gavin.business.DBTrans;
import com.jfinal.kit.PropKit;
import com.jfinal.template.Engine;

import io.jboot.Jboot;
import io.jboot.server.listener.JbootAppListenerBase;

/**
 * ------------------------------
 * 支付业务
 * ------------------------------
 * @author wdm（l311576@sina.com）  @date 2018年6月8日
 * @version 1.0
 */
public class MessageService extends JbootAppListenerBase{
	
	public static void main(String[] args) {

    	PropKit.use("jboot.properties");
		boolean dbstart=DBTrans.getInstance().config(PropKit.getProp().getProperties()).start();
		if(!dbstart){//数据库插件启动失败
			return;
		}
        Jboot.run(args);
	}
	
	@Override
	public void onJfinalEngineConfig(Engine engine) {
		engine.setBaseTemplatePath("template");
	}
}