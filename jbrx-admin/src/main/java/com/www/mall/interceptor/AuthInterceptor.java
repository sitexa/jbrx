package com.www.mall.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.www.mall.common.bean.RC;
import com.www.mall.common.bean.Ret;
import com.www.mall.common.shiro.ShiroUtils;
import io.jboot.web.controller.JbootController;

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
