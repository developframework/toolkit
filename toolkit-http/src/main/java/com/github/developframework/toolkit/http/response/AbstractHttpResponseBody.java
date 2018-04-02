package com.github.developframework.toolkit.http.response;

import lombok.Getter;

import java.io.IOException;
import java.net.HttpURLConnection;

/**
 * @author qiuzhenhao
 */
public abstract class AbstractHttpResponseBody<T> implements HttpResponseBody<T> {

    @Getter
    protected T bodyContent;

    public AbstractHttpResponseBody(HttpURLConnection connection) {
        try {
            this.bodyContent = parseBodyContent(connection);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
