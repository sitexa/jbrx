package com.sitexa.admin.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.sitexa.common.bean.Ret;
import com.sitexa.common.constants.RC;
import com.sitexa.common.shiro.ShiroUtils;
import io.jboot.web.controller.JbootController;

public class AuthInterceptor implements Interceptor {
    public void intercept(Invocation inv) {
        JbootController controller = (JbootController) inv.getController();
        Object principal= ShiroUtils.getSubject().getPrincipal();
        if(principal==null){
            controller.renderJson(Ret.result(RC.RELOGIN.getState(), "未认证"));
            return ;
        }
        inv.invoke();
    }
}
