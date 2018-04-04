package com.github.developframework.toolkit.http.request;

import java.net.HttpURLConnection;
import java.nio.charset.Charset;

/**
 * @author qiuzhenhao
 */
public abstract class RawHttpRequestBody extends HttpRequestBody{

    @Override
    public void prepare(HttpURLConnection connection, HttpRequest httpRequest) {
        connection.setRequestProperty("Content-Type", getContentType(httpRequest.getCharset()));
    }

    protected abstract String getContentType(Charset charset);

    @Override
    protected String serializeParameters(Charset charset) {
        return null;
    }
}
