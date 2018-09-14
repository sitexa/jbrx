package com.www.mall.common.utils.http.impl;


import javax.net.ssl.*;

import com.www.mall.common.utils.http.HttpBase;
import org.apache.log4j.Logger;
import com.www.mall.common.utils.http.HttpRequest;
import com.www.mall.common.utils.http.HttpResponse;
import com.www.mall.common.utils.StringUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.Map;

public class HttpImpl extends HttpBase {

    private static final Logger LOG = Logger.getLogger(HttpImpl.class);


    @Override
    public HttpResponse handle(HttpRequest request) {

        HttpResponse response = request.getDownloadFile() == null
                ? new HttpResponse()
                : new HttpResponse(request.getDownloadFile());
        doProcess(request, response);
        return response;
    }


    private void doProcess(HttpRequest request, HttpResponse response) {
        HttpURLConnection connection = null;
        InputStream stream = null;
        try {

            connection = getConnection(request);
            configConnection(connection, request);


            if (request.isGetRquest()) {
                connection.setInstanceFollowRedirects(true);
                connection.connect();

                if (connection.getResponseCode() >= 400) {
                    stream = connection.getErrorStream();
                } else {
                    stream = connection.getInputStream();
                }
            }
            /**
             * 处理 post请求
             */
            else if (request.isPostRquest()) {


                connection.setRequestMethod("POST");
                connection.setDoOutput(true);

                if (!request.isMultipartFormData()) {
                    String postContent = buildParams(request);
                    if (StringUtils.isNotEmpty(postContent)) {
                        DataOutputStream dos = new DataOutputStream(connection.getOutputStream());
                        dos.write(postContent.getBytes(request.getCharset()));
                        dos.flush();
                        dos.close();
                    }
                    stream = connection.getInputStream();
                }

                /**
                 * 处理文件上传
                 */
                else {

                    if (request.getParams() != null && request.getParams().size() > 0) {
                        String endFlag = "\r\n";
                        String boundary = "---------" + StringUtils.uuid();
                        connection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
                        DataOutputStream dos = new DataOutputStream(connection.getOutputStream());
                        for (Map.Entry entry : request.getParams().entrySet()) {
                            if (entry.getValue() instanceof File) {
                                File file = (File) entry.getValue();
                                checkFileNormal(file);
                                dos.writeBytes(boundary + endFlag);
                                dos.writeBytes(String.format("Content-Disposition: form-data; name=\"%s\"; filename=\"%s\"", entry.getKey(), file.getName()) + endFlag);
                                dos.writeBytes(endFlag);
                                FileInputStream fStream = new FileInputStream(file);
                                byte[] buffer = new byte[2028];
                                for (int len = 0; (len = fStream.read(buffer)) > 0; ) {
                                    dos.write(buffer, 0, len);
                                }

                                dos.writeBytes(endFlag);
                            } else {
                                dos.writeBytes("Content-Disposition: form-data; name=\"" + entry.getKey() + "\"");
                                dos.writeBytes(endFlag);
                                dos.writeBytes(endFlag);
                                dos.writeBytes(String.valueOf(entry.getValue()));
                                dos.writeBytes(endFlag);
                            }
                        }

                        dos.writeBytes("--" + boundary + "--" + endFlag);
                    }
                }
            }

            response.setContentType(connection.getContentType());
            response.setResponseCode(connection.getResponseCode());
            response.setHeaders(connection.getHeaderFields());

            response.pipe(stream);
            response.finish();


        } catch (Throwable ex) {
            LOG.warn(ex.toString(), ex);
            response.setError(ex);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void checkFileNormal(File file) {
        if (!file.exists()) {
            throw new RuntimeException("file not exists!!!!" + file);
        }
        if (file.isDirectory()) {
            throw new RuntimeException("cannot upload directory!!!!" + file);
        }
        if (!file.canRead()) {
            throw new RuntimeException("cannnot read file!!!" + file);
        }
    }


    private static void configConnection(HttpURLConnection connection, HttpRequest request) throws ProtocolException {
        if (connection == null)
            return;
        connection.setReadTimeout(request.getReadTimeOut());
        connection.setConnectTimeout(request.getConnectTimeOut());
        connection.setRequestMethod(request.getMethod());


        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.setRequestProperty("UserPrincipal-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.146 Safari/537.36");
        if (request.getHeaders() != null && request.getHeaders().size() > 0) {
            for (Map.Entry<String, String> entry : request.getHeaders().entrySet()) {
                connection.setRequestProperty(entry.getKey(), entry.getValue());
            }
        }
    }

    private static HttpURLConnection getConnection(HttpRequest request) {
        try {
            if (request.isGetRquest()) {
                buildGetUrlWithParams(request);
            }
            if (request.getRequestUrl().toLowerCase().startsWith("https")) {
                return getHttpsConnection(request);
            } else {
                return getHttpConnection(request.getRequestUrl());
            }
        } catch (Throwable ex) {
            throw new RuntimeException(ex);
        }
    }

    private static HttpURLConnection getHttpConnection(String urlStr) throws Exception {
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        return conn;
    }

    private static HttpsURLConnection getHttpsConnection(HttpRequest request) throws Exception {
        URL url = new URL(request.getRequestUrl());
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();

        if (request.getCertPath() != null && request.getCertPass() != null) {

            KeyStore clientStore = KeyStore.getInstance("PKCS12");
            clientStore.load(new FileInputStream(request.getCertPath()), request.getCertPass().toCharArray());

            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            keyManagerFactory.init(clientStore, request.getCertPass().toCharArray());
            KeyManager[] keyManagers = keyManagerFactory.getKeyManagers();


            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(clientStore);

            SSLContext sslContext = SSLContext.getInstance("TLSv1");
            sslContext.init(keyManagers, trustManagerFactory.getTrustManagers(), new SecureRandom());

            conn.setSSLSocketFactory(sslContext.getSocketFactory());

        } else {
            conn.setHostnameVerifier(hnv);
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            if (sslContext != null) {
                TrustManager[] tm = {trustAnyTrustManager};
                sslContext.init(null, tm, null);
                SSLSocketFactory ssf = sslContext.getSocketFactory();
                conn.setSSLSocketFactory(ssf);
            }
        }
        return conn;
    }

    private static X509TrustManager trustAnyTrustManager = new X509TrustManager() {
        public void checkClientTrusted(X509Certificate[] chain, String authType) {
        }

        public void checkServerTrusted(X509Certificate[] chain, String authType) {
        }

        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    };

    private static HostnameVerifier hnv = new HostnameVerifier() {
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    };


}
