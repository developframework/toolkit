package com.github.developframework.toolkit.http.request;

import com.github.developframework.toolkit.http.HttpHeader;
import lombok.Getter;
import lombok.Setter;

import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Http请求体
 * @author qiuzhenhao
 */
@Getter
public class HttpRequest {

    private String url;

    private Set<HttpHeader> headers = new HashSet<>();

    private Set<HttpUrlParameter> urlParameters = new HashSet<>();

    @Setter
    private Charset charset = Charset.forName("utf-8");

    @Setter
    private HttpRequestBody body;

    public HttpRequest(String url) {
        this.url = url;
    }

    public void addHeader(String headerName, String value) {
        headers.add(new HttpHeader(headerName, value));
    }

    public void addUrlParameter(String parameterName, Object value) {
        urlParameters.add(new HttpUrlParameter(parameterName, value));
    }

    public String getUrlFull() {
        return urlParameters.isEmpty() ? url : (url + "?" + serializeParameters());
    }

    /**
     * 序列化参数
     * a=1&b=2格式
     * @return
     */
    protected String serializeParameters() {
        String[] parameterStrArray = urlParameters.stream().map(parameter -> String.format("%s=%s", parameter.getParameterName(), parameter.getValue())).collect(Collectors.toList()).toArray(new String[urlParameters.size()]);
        return String.join("&", parameterStrArray);
    }

    /**
     * 判断是否存在Body
     * @return
     */
    public boolean hasBody() {
        return body != null;
    }
}
