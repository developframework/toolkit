package com.github.developframework.toolkit.http;

import lombok.Getter;

/**
 * Http头信息
 * @author qiuzhenhao
 */
@Getter
public class HttpHeader {

    private String headerName;

    private String value;

    public HttpHeader(String headerName, String value) {
        this.headerName = headerName;
        this.value = value;
    }

    @Override
    public String toString() {
        return String.format("%s: %s", headerName, value);
    }
}
