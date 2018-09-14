package com.www.mall;

import com.jfinal.config.Constants;
import com.jfinal.config.Interceptors;
import com.jfinal.json.JFinalJsonFactory;
import com.jfinal.render.ViewType;
import com.jfinal.template.Engine;
import com.www.mall.common.base.ValidatorInterceptor;
import com.www.mall.common.bean.RC;
import com.www.mall.common.render.AppRenderFactory;

import io.jboot.Jboot;
import io.jboot.server.listener.JbootAppListenerBase;

/**
 * @author wdm （l311576@sina.com）
 * @version 1.0
 */
public class ApiApp extends JbootAppListenerBase {

    public static void main(String args[]) {
        Jboot.run(args);
    }


    @Override
    public void onJfinalEngineConfig(Engine engine) {
    	engine.setDevMode(false);
//    	engine.setSourceFactory(new ClassPathSourceFactory());
//    	engine.setBaseTemplatePath("view");
    	super.onJfinalEngineConfig(engine);
    }
    
    @Override
    public void onJfinalConstantConfig(Constants constants) {
		constants.setErrorView(RC.REQUEST_FAIL.getState(), RC.REQUEST_FAIL.getDesc());
		constants.setErrorView(RC.NO_PERMISSION.getState(), RC.NO_PERMISSION.getDesc());
		constants.setErrorView(RC.RELOGIN.getState(), "登录信息过期,请重新登录");    	
		constants.setError401View("401");
		constants.setError403View("没有访问权限");//没有访问权限
		constants.setError404View("没有找到相关的服务");//没有找到相关的服务
		constants.setError500View("系统异常");//系统异常
        constants.setRenderFactory(new AppRenderFactory());
        constants.setJsonFactory(new JFinalJsonFactory());		
		
		constants.setViewType(ViewType.JFINAL_TEMPLATE);
		super.onJfinalConstantConfig(constants);
    }
    
	@Override
	public void onInterceptorConfig(Interceptors interceptors) {
		interceptors.add(new ValidatorInterceptor());
	}
}