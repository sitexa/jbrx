package com.www.mall.service;

import com.gavin.business.DBTrans;
import com.jfinal.kit.PropKit;

import io.jboot.Jboot;
import io.jboot.server.listener.JbootAppListenerBase;

/**
 * @author wdm （l311576@sina.com）
 * @version 1.0
 */
public class UserService extends JbootAppListenerBase {

    public static void main(String args[]) {
    	PropKit.use("jboot.properties");
		boolean dbstart=DBTrans.getInstance().config("jboot.properties").start();
		if(!dbstart){//数据库插件启动失败
			return;
		}
        Jboot.run(args);
    }

}

