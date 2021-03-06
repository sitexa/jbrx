package com.sitexa.common.utils.http;

import org.apache.log4j.Logger;

import java.io.*;
import java.util.List;
import java.util.Map;

public class HttpResponse {
    private static final Logger log = Logger.getLogger(HttpResponse.class);

    private String content;
    private OutputStream outputStream;
    private File file;
    private Throwable error;
    private Map<String, List<String>> headers;
    private int responseCode;
    private String contentType;

    public HttpResponse() {
        this.outputStream = new ByteArrayOutputStream();
    }

    public HttpResponse(File file) {
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdir();
        }

        if (file.exists()) {
            file.delete();
        }

        try {
            file.createNewFile();
            this.file = file;
            this.outputStream = new FileOutputStream(file);
        } catch (Exception e) {
            setError(e);
        }
    }


    /**
     * 获取数据内容
     *
     * @return
     */
    public String getContent() {
        if (content != null) {
            return content;
        }
        if (outputStream != null && outputStream instanceof ByteArrayOutputStream) {
            return new String(((ByteArrayOutputStream) outputStream).toByteArray());
        }
        return null;
    }

    public void setContent(String content) {
        this.content = content;
    }


    /**
     * 把 inputStream 写入response
     *
     * @param inputStream
     */
    public void pipe(InputStream inputStream) {
        try {
            byte[] buffer = new byte[1024];
            for (int len = 0; (len = inputStream.read(buffer)) > 0; ) {
                outputStream.write(buffer, 0, len);
            }
        } catch (Throwable throwable) {
            log.error(throwable.toString(), throwable);
            setError(throwable);
        }
    }

    /**
     * 结束response和释放资源
     */
    public void finish() {
        if (outputStream != null) {
            try {
                outputStream.flush();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isNotError() {
        return !isError();
    }

    public boolean isError() {
        return error != null;
    }

    public Throwable getError() {
        return error;
    }

    public void setError(Throwable error) {
        this.error = error;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public Map<String, List<String>> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, List<String>> headers) {
        this.headers = headers;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
