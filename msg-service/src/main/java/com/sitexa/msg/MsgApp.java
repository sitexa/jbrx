package com.sitexa.msg;

import com.gavin.business.DBTrans;
import com.jfinal.kit.PropKit;
import com.jfinal.template.Engine;
import io.jboot.Jboot;
import io.jboot.server.listener.JbootAppListenerBase;

public class MsgApp extends JbootAppListenerBase {
    public static void main(String[] args) {
        PropKit.use("jboot.properties");
        boolean dbstart = DBTrans.getInstance().config(PropKit.getProp().getProperties()).start();
        if (!dbstart) {
            return;
        }
        Jboot.run(args);
    }

    @Override
    public void onJfinalEngineConfig(Engine engine) {
        engine.setBaseTemplatePath("template");
    }
}
