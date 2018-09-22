package com.sitexa.api;

import com.jfinal.config.Constants;
import com.jfinal.config.Interceptors;
import com.jfinal.json.JFinalJsonFactory;
import com.jfinal.render.ViewType;
import com.jfinal.template.Engine;
import com.sitexa.common.base.ValidatorInterceptor;
import com.sitexa.common.render.AppRenderFactory;
import io.jboot.Jboot;
import io.jboot.server.listener.JbootAppListenerBase;

public class ApiApp extends JbootAppListenerBase {

    public static void main(String[] args) {
        Jboot.run(args);
    }

    @Override
    public void onJfinalEngineConfig(Engine engine) {
        engine.setDevMode(false);
        super.onJfinalEngineConfig(engine);
    }

    @Override
    public void onJfinalConstantConfig(Constants constants) {
        constants.setRenderFactory(new AppRenderFactory());
        constants.setJsonFactory(new JFinalJsonFactory());
        constants.setViewType(ViewType.JFINAL_TEMPLATE);
        super.onJfinalConstantConfig(constants);
    }

    @Override
    public void onInterceptorConfig(Interceptors interceptors) {
        interceptors.add(new ValidatorInterceptor());
        super.onInterceptorConfig(interceptors);
    }
}
