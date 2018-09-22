package com.sitexa.common.bean;

import com.gavin.model.Response;
import com.sitexa.common.constants.RC;
import com.sitexa.common.utils.JSONUtils;

import java.io.Serializable;

/**
 * ------------------------------
 * 通用返回数据结果-->json格式
 * 请使用Response
 * ------------------------------
 */
public class Ret implements Serializable {

    private static final long serialVersionUID = 6686417232952880028L;

    private int result;                    //返回数据状态
    private Object data;            //返回数据结果
    private String message;    //返回数据描述
    private String filePath; //文件路径

    public Ret() {
    }

    public Ret(RC rc) {
        this.result = rc.getState();
    }

    public Ret(int result, String message) {
        this.result = result;
        this.message = message;
    }

    //static start-----------------------------------------------------------
    public static Ret ok() {
        Ret br = new Ret(RC.SUCCESS);
        br.setMessage("OK");
        return br;
    }

    public static Ret ok(String message) {
        Ret br = new Ret(RC.SUCCESS);
        br.setMessage(message);
        return br;
    }

    public static Ret ok(String message, Object obj) {
        Ret br = new Ret(RC.SUCCESS);
        br.setMessage(message);
        br.setData(obj);
        return br;
    }

    public static Ret resultFile(Object obj, String filePath) {
        if (obj == null) {
            return Ret.fail("请求失败或数据为空");
        }
        Ret br = new Ret(RC.SUCCESS);
        br.setMessage("OK");
        br.setData(obj);
        br.setFilePath(filePath);
        return br;
    }

    public static Ret result(Object obj) {
        if (obj == null) {
            return Ret.fail("请求错误");
        } else {
            return Ret.ok("OK", obj);
        }
    }


    public static Ret result(RC rc, String message, Object data) {
        Ret br = new Ret();
        br.setResult(rc.getState());
        br.setMessage(message);
        br.setData(data);
        return br;
    }


    public static Ret result(int result, String message, Object data) {
        Ret br = new Ret();
        br.setResult(result);
        br.setMessage(message);
        br.setData(data);
        return br;
    }

    public static Ret relogin(String message) {
        return Ret.result(RC.RELOGIN, message, "{}");
    }


    public static Ret noPermission() {
        return Ret.result(RC.NO_PERMISSION, "没有访问权限", "{}");
    }


    public static Ret paramFail(String resultDesc) {
        Ret br = new Ret(RC.PARAM_FAIL);
        br.setMessage(resultDesc);
        return br;
    }

    public static Ret fail(String resultDesc) {
        Ret br = new Ret(RC.BUSINESS_FAIL);
        br.setMessage(resultDesc);
        return br;
    }


    public static Ret result(boolean ok, String resultDesc) {
        if (ok) {
            return Ret.ok(resultDesc);
        } else {
            return Ret.fail(resultDesc);
        }
    }

    public static Ret result(int result, String message) {
        Ret br = new Ret(result, message);
        return br;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public boolean success() {
        return this.result == RC.SUCCESS.getState();
    }

    public static Ret ret(Ret ret) {
        if (ret == null) {
            return Ret.fail("请求异常");
        }
        return ret;
    }

    private static Ret from(Response response) {
        Ret ret = new Ret(response.result, response.message);
        if (response.success()) {
            ret.setData(response.toJson());
        }
        return ret;
    }

    public static String from2Json(Response response) {
        return from(response).toString();
    }

    public static Ret ifNull(Object obj) {
        if (obj == null) {
            return Ret.fail("请求失败");
        }
        return Ret.result(RC.SUCCESS, "成功", obj);
    }

    public static Ret ifNull(Ret ret) {
        if (ret == null) {
            return Ret.fail("请求失败");
        }
        return ret;
    }

    public static String append(String[] names, Object... objs) {
        StringBuffer sb = new StringBuffer("{");

        sb.append("\"").append(names[0]).append("\":");
        sb.append(objs[0].toString());

        for (int i = 1; i < objs.length; i++) {
            sb.append(",");
            sb.append("\"").append(names[i]).append("\":");
            sb.append(objs[i].toString());
        }
        sb.append("}");
        return sb.toString();
    }


    public String toString() {
        if (data instanceof String) {
            StringBuilder sb = new StringBuilder("{");
            sb.append("\"result\":").append(result).append(",");
            sb.append("\"message\":\"").append(message).append("\",");
            sb.append("\"data\":");
            //如果是json格式的数据则设置为json数据
            if (data != null && ((String) data).indexOf("{") > -1 && ((String) data).indexOf("}") > 0) {
                sb.append(data);
            } else {
                sb.append("\"" + data + "\"");
            }
            sb.append("}");

            return sb.toString();
        } else {

            return JSONUtils.beanToJson(this);
        }
    }

}
