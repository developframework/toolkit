package com.github.developframework.toolkit.http.request;

import java.net.HttpURLConnection;
import java.nio.charset.Charset;

/**
 * @author qiuzhenhao
 */
public interface HttpRequestBody {

    void prepare(HttpURLConnection connection, HttpRequest httpRequest);

    byte[] serializeBody(Charset charset);
}
