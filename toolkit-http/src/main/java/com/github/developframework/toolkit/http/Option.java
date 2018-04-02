package com.github.developframework.toolkit.http;

import lombok.Data;

/**
 * @author qiuzhenhao
 */
@Data
public class Option {

    private int connectTimeout = 5000;
    private int readTimeout = 10000;
}
