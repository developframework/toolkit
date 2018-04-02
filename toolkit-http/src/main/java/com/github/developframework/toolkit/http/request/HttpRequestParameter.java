package com.github.developframework.toolkit.http.request;

import lombok.Getter;

/**
 * @author qiuzhenhao
 */
@Getter
public class HttpRequestParameter {

    private String parameterName;

    private Object value;

    public HttpRequestParameter(String parameterName, Object value) {
        this.parameterName = parameterName;
        this.value = value;
    }
}
