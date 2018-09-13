package com.www.mall.common.utils.http.impl;

import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import com.www.mall.common.utils.http.HttpBase;
import com.www.mall.common.utils.http.HttpRequest;
import com.www.mall.common.utils.http.HttpResponse;
import org.apache.log4j.Logger;


import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OKHttpImpl extends HttpBase {
    private static final Logger LOG = Logger.getLogger(OKHttpImpl.class);

    private String dataType="string";
    
    public OKHttpImpl() {
    }

    public OKHttpImpl(String dataType) {
    	this.dataType=dataType;
    }
    
    @Override
    public HttpResponse handle(HttpRequest request) {

        HttpResponse response = request.getDownloadFile() == null
                ? new HttpResponse()
                : new HttpResponse(request.getDownloadFile());
        doProcess(request, response);

        return response;
    }


    private void doProcess(HttpRequest request, HttpResponse response) {
        try {
            if (request.isPostRquest()) {
                doProcessPostRequest(request, response);
            }

            /**
             * get 请求
             */
            else if (request.isGetRquest()) {
                buildGetUrlWithParams(request);
                doProcessGetRequest(request, response);
            }

        } catch (Throwable ex) {
            LOG.error(ex.toString(), ex);
            response.setError(ex);
        }
    }

    private void doProcessGetRequest(HttpRequest request, HttpResponse response) throws Exception {
        Request okHttpRequest = new Request.Builder()
                .url(request.getRequestUrl())
                .build();


        doProcessRequest(request, response, okHttpRequest);
    }

    private void doProcessPostRequest(final HttpRequest request, HttpResponse response) throws Exception {
        RequestBody requestBody = null;
        if (request.isMultipartFormData()) {
            MultipartBody.Builder builder = new MultipartBody.Builder();
            for (Map.Entry<String, Object> entry : request.getParams().entrySet()) {
                if (entry.getValue() instanceof File) {
                    File file = (File) entry.getValue();
                    builder.addFormDataPart(entry.getKey(), file.getName(), RequestBody.create(MediaType.parse("application/octet-stream"), file));
                } else {
                    builder.addFormDataPart(entry.getKey(), entry.getValue() == null ? "" : entry.getValue().toString());
                }
            }
            requestBody = builder.build();
        } else {
            FormBody.Builder builder = new FormBody.Builder();
            for (Map.Entry<String, Object> entry : request.getParams().entrySet()) {
                builder.add(entry.getKey(), entry.getValue() == null ? "" : entry.getValue().toString());
            }
            requestBody = builder.build();
        }


        Request okHttpRequest = new Request.Builder().url(request.getRequestUrl())
                .post(requestBody)
                .build();


        doProcessRequest(request, response, okHttpRequest);
    }

    private void doProcessRequest(HttpRequest request, HttpResponse response, Request okHttpRequest) throws Exception {
        OkHttpClient client = getClient(request);
       // okHttpRequest.newBuilder().addHeader("token",request.getHeaders().get("token"));
        Call call = client.newCall(okHttpRequest);
        Response okHttpResponse = call.execute();
        LOG.info("----------------   dataType:"+dataType+"  contentType:"+okHttpResponse.body().contentType()+"  ----------------");
        response.setResponseCode(okHttpResponse.code());
        response.setContentType(okHttpResponse.body().contentType().type());
       if(!"string".equals(dataType)){
    	   response.pipe(okHttpResponse.body().byteStream());
    	   response.finish();
       }else{
    	   response.setContent(okHttpResponse.body().string());
       }
    }


    private OkHttpClient getClient(HttpRequest request) throws Exception {
        if (request.getRequestUrl().toLowerCase().startsWith("https")) {
            return getHttpsClient(request);
        }

        return new OkHttpClient();
    }

    public OkHttpClient getHttpsClient(HttpRequest request) throws Exception {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (request.getCertPath() != null && request.getCertPass() != null) {
            KeyStore clientStore = KeyStore.getInstance("PKCS12");
            clientStore.load(new FileInputStream(request.getCertPath()), request.getCertPass().toCharArray());

            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            keyManagerFactory.init(clientStore, request.getCertPass().toCharArray());
            KeyManager[] keyManagers = keyManagerFactory.getKeyManagers();


            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(clientStore);

            TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();


            SSLContext sslContext = SSLContext.getInstance("TLSv1");
            sslContext.init(keyManagers, trustManagers, new SecureRandom());

            X509TrustManager x509TrustManager = trustAnyTrustManager;
            if (trustManagers != null && trustManagers.length > 0 && trustManagers[0] instanceof X509TrustManager) {
                x509TrustManager = (X509TrustManager) trustManagers[0];
            }

            builder.sslSocketFactory(sslContext.getSocketFactory(), x509TrustManager);

        } else {
            builder.hostnameVerifier(hnv);
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            if (sslContext != null) {
                TrustManager[] trustManagers = {trustAnyTrustManager};
                sslContext.init(null, trustManagers, new SecureRandom());
                builder.sslSocketFactory(sslContext.getSocketFactory(), trustAnyTrustManager);
            }
        }

        return builder.build();
    }


    private static X509TrustManager trustAnyTrustManager = new X509TrustManager() {
        public void checkClientTrusted(X509Certificate[] chain, String authType) {
        }

        public void checkServerTrusted(X509Certificate[] chain, String authType) {
        }

        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    };

    private static HostnameVerifier hnv = new HostnameVerifier() {
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    };
}
