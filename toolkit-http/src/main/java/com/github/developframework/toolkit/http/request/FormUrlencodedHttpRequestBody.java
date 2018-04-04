package com.github.developframework.toolkit.http.request;

import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
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
        return serializeParameters(charset).getBytes(charset);
    }

    /**
     * 序列化参数
     * a=1&b=2格式
     * @return
     */
    protected String serializeParameters(Charset charset) {
        String[] parameterStrArray = parameters.stream().map(parameter -> {
            try {
                return String.format("%s=%s", parameter.getParameterName(), URLEncoder.encode(parameter.getValue().toString(), charset.displayName()));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return "";
            }
        }).collect(Collectors.toList()).toArray(new String[parameters.size()]);
        return String.join("&", parameterStrArray);
    }
}
