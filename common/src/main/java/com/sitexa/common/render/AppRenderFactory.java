package com.sitexa.common.render;

import com.jfinal.render.Render;
import io.jboot.web.render.JbootRenderFactory;

public class AppRenderFactory extends JbootRenderFactory {

    @Override
    public Render getRender(String view) {
        return super.getRender(view);
    }

    @Override
    public Render getErrorRender(int errorCode) {
        return new JsonErrorRender(errorCode, constants.getErrorView(errorCode));
    }

}
