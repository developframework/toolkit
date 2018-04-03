package com.github.developframework.toolkit.http.response;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.net.HttpURLConnection;

/**
 * 简单型响应体处理器
 * 转化为字符串
 *
 * @author qiuzhenhao
 */
public class SimpleHttpResponseBodyProcessor extends HttpResponseBodyProcessor<String, String> {

    @Override
    public boolean checkSuccess(HttpURLConnection connection) throws IOException {
        int httpStatus = connection.getResponseCode();
        return httpStatus >= 200 && httpStatus < 300;
    }

    @Override
    public String parseBodyContent(HttpURLConnection connection) throws IOException {
        StringBuffer sb = new StringBuffer();
        IOUtils.readLines(connection.getInputStream(), "utf-8").forEach(line -> sb.append(line).append('\n'));
        return sb.toString();
    }

    @Override
    public String error(HttpURLConnection connection) throws IOException {
        StringBuffer sb = new StringBuffer();
        IOUtils.readLines(connection.getErrorStream(), "utf-8").forEach(line -> sb.append(line).append('\n'));
        return sb.toString();
    }
}
