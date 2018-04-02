package com.github.developframework.toolkit.http;

import com.github.developframework.toolkit.http.response.AbstractHttpResponseBody;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.net.HttpURLConnection;

/**
 * @author qiuzhenhao
 */
public class ToStringHttpResponseBody extends AbstractHttpResponseBody<String> {

    public ToStringHttpResponseBody(HttpURLConnection connection) {
        super(connection);
    }

    @Override
    public String parseBodyContent(HttpURLConnection connection) throws IOException {
        StringBuffer sb = new StringBuffer();
        IOUtils.readLines(connection.getInputStream(), "utf-8").forEach(line -> sb.append(line).append("\n"));
        return sb.toString();
    }
}
