package com.github.developframework.toolkit.http.request;

import lombok.Getter;

import java.net.HttpURLConnection;
import java.nio.charset.Charset;

/**
 * @author qiuzhenhao
 */
public class FormDataHttpRequestBody extends HttpRequestBody {

    @Getter
    private String boundary;

    public FormDataHttpRequestBody(String boundary) {
        this.boundary = boundary;
    }

    @Override
    public void prepare(HttpURLConnection connection, HttpRequest httpRequest) {
        connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
    }

    @Override
    public byte[] serializeBody(Charset charset) {
        return serializeParameters(charset).getBytes(charset);
    }

    @Override
    protected String serializeParameters(Charset charset) {
        String data =  "Content-Type: multipart/form-data; boundary=" + boundary + "\r\n\r\n"+ boundary + "\r\nContent-Disposition: form-data; name=\"name\"\r\n\r\nxxxx\r\n" + boundary;
        System.out.println(data);
        return data;
    }
}
