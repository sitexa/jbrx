package com.www.mall.service;

import com.gavin.business.DBTrans;
import com.jfinal.template.Engine;
import com.www.mall.common.bean.Ret;
import com.www.mall.service.pay.api.PayConfig;

public class BaseTest {
	
	static{
		boolean dbstart=DBTrans.getInstance().config("jboot.properties").start();
		Engine.use().setBaseTemplatePath(PayConfig.TEMPLATEPATH); //C:\\Users\\Administrator\\Documents\\rongxin_java2.0\\rxmall-pay-service\\src\\main\\resources\\template
		if(!dbstart){//数据库插件启动失败
			System.out.println("数据库启动失败");
		}
	}
	
	public static void result(Object obj){
		System.out.println(Ret.ok("ok", obj));
	}

}
