package com.www.mall.common.render;

import com.jfinal.render.Render;

public abstract class BaseErrorRender extends Render {

    public BaseErrorRender(int result, String message) {
        this.result = result;
        this.message = message;
    }

    /** 错误码 */
    protected int result;

    /** 错误异常内容 */
    protected String message;

    public int getResult() {
        return result;
    }

    public String getMessage() {
        return message;
    }
}
