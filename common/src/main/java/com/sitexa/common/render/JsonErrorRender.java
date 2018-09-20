package com.sitexa.common.render;

import com.jfinal.render.RenderException;
import com.sitexa.common.bean.Ret;
import com.sitexa.common.constants.RC;

import java.io.IOException;
import java.io.PrintWriter;

public class JsonErrorRender extends BaseErrorRender {

    public JsonErrorRender(int result, String message) {
        super(result, message);
    }

    @Override
    public void render() {
        response.setStatus((getResult()== RC.NO_PERMISSION.getState()||getResult()==RC.RELOGIN.getState()||getResult()==RC.REQUEST_FAIL.getState())?200:getResult());

        PrintWriter writer = null;
        try {
            response.setContentType("application/json;charset=UTF-8");
            writer = response.getWriter();
            writer.write(Ret.result(getResult(), getMessage()).toString());
            writer.flush();
        } catch (IOException e) {
            throw new RenderException(e);
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}
