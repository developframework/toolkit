package com.github.developframework.toolkit.http.request;

import lombok.Getter;

import java.net.HttpURLConnection;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Set;

/**
 * @author qiuzhenhao
 */
public abstract class HttpRequestBody {

    @Getter
    protected Set<HttpRequestParameter> parameters = new HashSet<>();


    public void addParameter(String parameterName, String value) {
        parameters.add(new HttpRequestParameter(parameterName, value));
    }

    /**
     * 序列化参数
     * a=1&b=2格式
     * @return
     */
    protected abstract String serializeParameters();

    public abstract void prepare(HttpURLConnection connection, HttpRequest httpRequest);

    public abstract byte[] serializeBody(Charset charset);
}
