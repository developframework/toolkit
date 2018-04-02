package com.github.developframework.toolkit.http.response;

import java.io.IOException;
import java.net.HttpURLConnection;

/**
 * @author qiuzhenhao
 */
public interface HttpResponseBody<T> {

    T parseBodyContent(HttpURLConnection connection) throws IOException;
}
