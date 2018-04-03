package com.github.developframework.toolkit.http;

import lombok.Getter;
import lombok.Setter;

import java.net.InetSocketAddress;
import java.net.Proxy;

/**
 * @author qiuzhenhao
 */
@Getter
public class Option {

    @Setter
    private int connectTimeout = 5000;
    @Setter
    private int readTimeout = 10000;

    private Proxy proxy;

    public void setProxy(Proxy.Type type, String proxyHost, int proxyPort) {
        proxy = new Proxy(type, new InetSocketAddress(proxyHost, proxyPort));
    }
}
