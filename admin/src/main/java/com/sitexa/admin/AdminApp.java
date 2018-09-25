package com.sitexa.admin;

import com.jfinal.config.Constants;
import com.jfinal.config.Interceptors;
import com.jfinal.config.Routes;
import com.jfinal.json.JFinalJsonFactory;
import com.jfinal.render.ViewType;
import com.jfinal.template.Engine;
import com.jfinal.template.source.ClassPathSourceFactory;

import com.sitexa.common.base.ValidatorInterceptor;
import com.sitexa.common.constants.RC;
import com.sitexa.common.render.AppRenderFactory;
import io.jboot.Jboot;
import io.jboot.server.listener.JbootAppListenerBase;

public class AdminApp extends JbootAppListenerBase {

    public static void main(String args[]) {
//    	PropKit.use("jboot.properties");
        Jboot.run(args);
    }

    @Override
    public void onJfinalEngineConfig(Engine engine) {
    	engine.setDevMode(false);
		engine.setSourceFactory(new ClassPathSourceFactory());
		engine.setBaseTemplatePath("view");
		super.onJfinalEngineConfig(engine);
        
//        AppInfo app = Jboot.config(AppInfo.class);
//        engine.addSharedObject("APP", app);
//        engine.addSharedObject("RESOURCE_HOST", app.getResourceHost());    
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
    public void onJfinalRouteConfig(Routes routes) {
//    	routes.setBaseViewPath("view");
    }
    
//	@Override
//	public void onFixedInterceptorConfig(FixedInterceptors fixedInterceptors) {
//		fixedInterceptors.add(new ValidatorInterceptor());
//		super.onFixedInterceptorConfig(fixedInterceptors);
//	}
    
    @Override
    public void onInterceptorConfig(Interceptors interceptors) {
    	interceptors.addGlobalActionInterceptor(new ValidatorInterceptor());
    	super.onInterceptorConfig(interceptors);
    }
}
