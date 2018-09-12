package com.www.mall.common.utils.http;

import com.www.mall.common.utils.StringUtils;
import com.www.mall.common.utils.http.impl.Http;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;


public abstract class HttpBase implements Http {

    public static String buildParams(HttpRequest request) throws UnsupportedEncodingException {
        Map<String, Object> params = request.getParams();
        if (params == null || params.isEmpty()) {
            return null;
        }

        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            if (entry.getKey() != null && StringUtils.isNotBlank(entry.getValue()))
                builder.append(entry.getKey().trim()).append("=")
                        .append(URLEncoder.encode(entry.getValue().toString(), request.getCharset())).append("&");
        }

        if (builder.charAt(builder.length() - 1) == '&') {
            builder.deleteCharAt(builder.length() - 1);
        }

        return builder.toString();
    }


    public static void buildGetUrlWithParams(HttpRequest request) throws UnsupportedEncodingException {

        String params = buildParams(request);

        if (StringUtils.isBlank(params)) {
            return;
        }

        String originUrl = request.getRequestUrl();
        if (originUrl.contains("?")) {
            originUrl = originUrl + "&" + params;
        } else {
            originUrl = originUrl + "?" + params;
        }
        request.setRequestUrl(originUrl);

    }


}
