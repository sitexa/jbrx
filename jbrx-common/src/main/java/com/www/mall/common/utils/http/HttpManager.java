package com.www.mall.common.utils.http;


import com.www.mall.common.utils.http.impl.Http;
import com.www.mall.common.utils.http.impl.HttpImpl;
import com.www.mall.common.utils.http.impl.OKHttpImpl;
public class HttpManager {

	private HttpManager() {}

	private static class HttpHandler {
		private static HttpManager instance = new HttpManager();
	}
	
    public static HttpManager me() {
        return HttpHandler.instance;
    }


    private Http http;

    public Http getHttp() {
        if (http == null) {
        	http = buildHttp();
        }
        return http;
    }


    private Http buildHttp() {
        String httpType=HttpConfig.TYPE_OKHTTP;
        switch (httpType) {
            case HttpConfig.TYPE_DEFAULT:
                return new HttpImpl();
            case HttpConfig.TYPE_OKHTTP:
                return new OKHttpImpl();
            case HttpConfig.TYPE_HTTPCLIENT:
                throw new RuntimeException("not finished!!!!");
            default:
            	 return new HttpImpl();
        }

    }


}
