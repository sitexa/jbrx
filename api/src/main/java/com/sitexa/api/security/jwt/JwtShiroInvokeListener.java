package com.sitexa.api.security.jwt;

import java.util.Map;

import com.sitexa.common.constants.RC;
import com.sitexa.common.shiro.sso.AuthType;
import com.sitexa.common.shiro.sso.SSOAuthenticationToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;

import com.jfinal.core.Controller;
import com.jfinal.log.Log;

import io.jboot.component.jwt.JwtManager;
import io.jboot.component.shiro.JbootShiroInvokeListener;
import io.jboot.component.shiro.processer.AuthorizeResult;
import io.jboot.utils.StringUtils;
import io.jboot.web.controller.JbootController;
import io.jboot.web.fixedinterceptor.FixedInvocation;

/**
 * ------------------------------
 * jwt sso shiro listener
 * ------------------------------
 */
public class JwtShiroInvokeListener implements JbootShiroInvokeListener {

    private final static Log log = Log.getLog(JwtShiroInvokeListener.class);

    @Override
    public void onInvokeBefore(FixedInvocation inv) {
        JbootController controller = (JbootController) inv.getController();
        String jwtToken = controller.getHeader(JwtManager.me().getHttpHeaderName());

        //过滤登陆相关的请求，不做处理
        String path = controller.getRequest().getPathInfo();
        if(String.valueOf(path).startsWith("/admin/login")){
            return;
        }

        if (StringUtils.isBlank(jwtToken)) {
        	log.warn("没有Jwt登录信息");
            inv.invoke();
            return;
        }
        log.info("携带Jwt登录信息");

        Map<String,Object> user = JwtManager.me().getPara("user");
        if(user==null){
        	log.warn("Jwt登录信息已过期");
        	doProcessUnauthenticated(controller);
        	return;
        }
        AuthenticationToken token = new SSOAuthenticationToken(AuthType.token,user.get("id")+"", jwtToken);

        try {
        	log.info("Jwt登录信息准备认证");
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);
            log.info("Jwt登录信息认证成功");
        } catch (Exception e) {
            log.error("Jwt登录信息认证失败："+e.getMessage());
        }
    }
    
    @Override
    public void onInvokeAfter(FixedInvocation inv, AuthorizeResult result) {
        if (result == null || result.isOk()) {
        	log.info("调用方法");
            inv.invoke();
            return;
        }

        int errorCode = result.getErrorCode();
        log.warn("Jwt登录信息授权认证失败："+errorCode);
        switch (errorCode) {
            case AuthorizeResult.ERROR_CODE_UNAUTHENTICATED:
                doProcessUnauthenticated(inv.getController());
                break;
            case AuthorizeResult.ERROR_CODE_UNAUTHORIZATION:
                doProcessuUnauthorization(inv.getController());
            break;
            default:
                doProcessuDefault(inv.getController());
        }
    }
    

    /**
     * 未进行身份认证
     * @param controller
     */
    public void doProcessUnauthenticated(Controller controller) {
    	controller.renderError(RC.RELOGIN.getState());
    }


    /**
     * 其他处理
     * @param controller
     */
    private void doProcessuDefault(Controller controller) {
        controller.renderError(RC.REQUEST_FAIL.getState());
    }

    /**
     * 无授权信息处理
     * @param controller
     */
    private void doProcessuUnauthorization(Controller controller) {
        controller.renderError(RC.NO_PERMISSION.getState());
    }
}
