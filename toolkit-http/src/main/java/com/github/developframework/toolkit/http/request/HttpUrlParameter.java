package com.github.developframework.toolkit.http.request;

import lombok.Getter;

/**
 * @author qiuzhenhao
 */
@Getter
public class HttpUrlParameter {

    private String parameterName;

    private Object value;

    public HttpUrlParameter(String parameterName, Object value) {
        this.parameterName = parameterName;
        this.value = value;
    }
}
