package com.www.mall.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.www.mall.common.bean.RC;
import com.www.mall.common.bean.Ret;
import com.www.mall.common.shiro.ShiroUtils;

import io.jboot.web.controller.JbootController;

/**
 * 
 * @ClassName:  LoginInterceptor   
 * @Description:验证用户是否已登录拦截器   
 * @author: 湖南无为网电子商务有限公司  (Dieudonne)
 * @date:   2018年7月11日 上午9:55:29   
 *     
 * @Copyright: 2018 http://www.0731333.com Inc. All rights reserved. 
 * 注意：本内容仅限于湖南无为网电子商务有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public class AuthInterceptor implements Interceptor {	
	public void intercept(Invocation inv) {
		JbootController controller = (JbootController) inv.getController();
		Object principal=ShiroUtils.getSubject().getPrincipal();
		if(principal==null){
			controller.renderJson(Ret.result(RC.RELOGIN.getState(), "未认证"));
			return ;
		}
		inv.invoke();
	}
	
}
