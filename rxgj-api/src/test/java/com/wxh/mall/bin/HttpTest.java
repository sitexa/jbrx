package com.wxh.mall.bin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.www.mall.common.bean.Ret;
import com.www.mall.common.utils.WebServiceUtils;
import com.www.mall.common.utils.http.HttpManager;
import com.www.mall.common.utils.http.HttpRequest;
import com.www.mall.common.utils.http.HttpResponse;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class HttpTest {
    public static void main(String[] args){
//        JSONObject json=new JSONObject();
//        json.put("strServiceName", "www.sojson.com");
//        json.put("strTransName", "");
//        json.put("city","%E5%8C%97%E4%BA%AC");
//       // http://api.showji.com/Locating/www.showji.com.aspx?m=手机号&output=json&callback=querycallback
//        WebServiceUtils.send("https://www.sojson.com/open/api/weather/json.shtml?city=%E5%8C%97%E4%BA%AC",json);
        String regex = "\\-?[1-9]\\d+";
        String columnValue="1";
        if(!"1".equals(columnValue) && !"2".equals(columnValue)){
            System.out.println("==");
        }

        //aa();
    }

    public static void aa(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("city","%E5%8C%97%E4%BA%AC");

        Ret br=WebServiceUtils.sendHttp("https://www.sojson.com/open/api/weather/json.shtml",params,HttpRequest.METHOD_GET);
        System.out.println(br);
    }


}
