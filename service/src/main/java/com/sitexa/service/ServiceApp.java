package com.sitexa.service;

import com.gavin.business.DBTrans;
import com.jfinal.kit.PropKit;
import io.jboot.Jboot;

public class ServiceApp {
    public static void main(String[] args) {
        PropKit.use("jboot.properties");
        boolean dbstart = DBTrans.getInstance().config("jboot.properties").start();
        if (!dbstart) {
            return;
        }
        Jboot.run(args);
    }
}
