package com.www.mall.common.utils.http.impl;


import com.www.mall.common.utils.http.HttpRequest;
import com.www.mall.common.utils.http.HttpResponse;

public interface Http {

    public HttpResponse handle(HttpRequest request);

}
