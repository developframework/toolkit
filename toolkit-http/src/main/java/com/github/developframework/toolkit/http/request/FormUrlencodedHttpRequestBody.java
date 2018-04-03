package com.github.developframework.toolkit.http.request;

import java.net.HttpURLConnection;
import java.nio.charset.Charset;
import java.util.stream.Collectors;

/**
 * @author qiuzhenhao
 */
public class FormUrlencodedHttpRequestBody extends HttpRequestBody {

    @Override
    public void prepare(HttpURLConnection connection, HttpRequest httpRequest) {
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + httpRequest.getCharset().displayName());
    }

    @Override
    public byte[] serializeBody(Charset charset) {
        return serializeParameters().getBytes(charset);
    }

    protected String serializeParameters() {
        String[] parameterStrArray = parameters.stream().map(parameter -> String.format("%s=%s", parameter.getParameterName(), parameter.getValue())).collect(Collectors.toList()).toArray(new String[parameters.size()]);
        return String.join("&", parameterStrArray);
    }
}
