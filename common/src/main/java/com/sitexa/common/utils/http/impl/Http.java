package com.sitexa.common.utils.http.impl;


import com.sitexa.common.utils.http.HttpRequest;
import com.sitexa.common.utils.http.HttpResponse;

public interface Http {

    public HttpResponse handle(HttpRequest request);

}
