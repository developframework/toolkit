package com.github.developframework.toolkit.http.request;

import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

/**
 * @author qiuzhenhao
 */
public abstract class AbstractHttpRequestBody implements HttpRequestBody {

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
}
