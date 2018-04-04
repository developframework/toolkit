package com.github.developframework.toolkit.http.response;

import lombok.Getter;

import java.io.IOException;
import java.net.HttpURLConnection;

/**
 * 响应体Body处理器
 * @param <T> 请求成功时的返回类型
 * @param <Y> 请求失败时的返回类型
 */
public abstract class HttpResponseBodyProcessor<T, Y> {

    @Getter
    protected boolean success;

    @Getter
    protected T content;

    @Getter
    protected Y error;

    public final void parseBody(HttpURLConnection connection) throws IOException {
        success = checkSuccess(connection);
        if(success) {
            content = parseBodyContent(connection);
        } else {
            error = error(connection);
        }
    }

    /**
     * 实现如何判断请求成功
     * @param connection
     * @return
     * @throws IOException
     */
    protected abstract boolean checkSuccess(HttpURLConnection connection) throws IOException;

    /**
     * 实现如何解析请求成功时的Body
     * @param connection
     * @return
     * @throws IOException
     */
    protected abstract T parseBodyContent(HttpURLConnection connection) throws IOException;

    /**
     * 实现请求失败时的处理
     * @param connection
     * @return
     * @throws IOException
     */
    protected abstract Y error(HttpURLConnection connection) throws IOException;
}
