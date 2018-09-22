package com.www.mall;

import com.gavin.business.DBTrans;
import com.jfinal.kit.PropKit;
import com.jfinal.template.Engine;
import com.jfinal.template.source.ClassPathSourceFactory;

import io.jboot.Jboot;
import io.jboot.server.listener.JbootAppListenerBase;

/**
 * ------------------------------
 * 文件系统
 * ------------------------------
 * @author wdm（l311576@sina.com）  @date 2018年6月8日
 * @version 1.0
 */
public class FileApp extends JbootAppListenerBase{
	
	public static void main(String[] args) {
    	PropKit.use("config.properties");
		boolean dbstart=DBTrans.getInstance().config("config.properties").start();
		if(!dbstart){//数据库插件启动失败
			return;
		}
        Jboot.run(args);
	}
	@Override
	public void onJfinalEngineConfig(Engine engine) {
		engine.setDevMode(true);
		engine.setSourceFactory(new ClassPathSourceFactory());
		engine.setBaseTemplatePath("view");
		super.onJfinalEngineConfig(engine);
	}
}