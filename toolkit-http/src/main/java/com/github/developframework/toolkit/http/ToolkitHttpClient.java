package com.github.developframework.toolkit.http;

import com.github.developframework.toolkit.base.Toolkit;
import com.github.developframework.toolkit.http.request.HttpRequest;
import com.github.developframework.toolkit.http.request.HttpRequestBody;
import com.github.developframework.toolkit.http.response.HttpResponse;
import com.github.developframework.toolkit.http.response.HttpResponseBodyProcessor;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Set;

/**
 * @author qiuzhenhao
 */
@Setter
@Getter
public final class ToolkitHttpClient {

    private Option option;

    public ToolkitHttpClient() {
        this(new Option());
    }

    public ToolkitHttpClient(Option option) {
        this.option = option;
    }


    /**
     * 发送请求
     * @param httpMethod
     * @param request
     * @param responseBodyClass
     * @param <T>
     * @return
     * @throws IOException
     */
    public <T extends HttpResponseBodyProcessor> HttpResponse<T> request(HttpMethod httpMethod, HttpRequest request, Class<T> responseBodyClass) throws IOException {
        switch (httpMethod) {
            case GET: {
                return this.get(request, responseBodyClass);
            }
            case POST: {
                return this.post(request, responseBodyClass);
            }
            case PUT: {
                return this.put(request, responseBodyClass);
            }
            case DELETE: {
                return this.delete(request, responseBodyClass);
            }
            default:
                return null;
        }
    }

    /**
     * 发送GET请求
     *
     * @param request
     * @param responseBodyClass
     * @param <T>
     * @return
     * @throws IOException
     */
    public <T extends HttpResponseBodyProcessor> HttpResponse<T> get(HttpRequest request, Class<T> responseBodyClass) throws IOException {
        URL url = new URL(request.getUrlFull());
        HttpURLConnection connection;
        if(Toolkit.exist(option.getProxy())) {
            connection = (HttpURLConnection) url.openConnection(option.getProxy());
        } else {
            connection = (HttpURLConnection) url.openConnection();
        }
        connectionSettings(connection, HttpMethod.GET, request, option);
        connection.setDoOutput(false);
        connection.setDoInput(true);

        // 设置header
        setRequestHeader(connection, request.getHeaders());
        connection.connect();

        // 构造response
        try {
            return generateHttpResponse(connection, responseBodyClass);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            connection.disconnect();
        }
    }

    /**
     * 发送POST请求
     *
     * @param request
     * @param responseBodyClass
     * @param <T>
     * @return
     * @throws IOException
     */
    public <T extends HttpResponseBodyProcessor> HttpResponse<T> post(HttpRequest request, Class<T> responseBodyClass) throws IOException {

        URL url = new URL(request.getUrlFull());
        HttpURLConnection connection;
        if(Toolkit.exist(option.getProxy())) {
            connection = (HttpURLConnection) url.openConnection(option.getProxy());
        } else {
            connection = (HttpURLConnection) url.openConnection();
        }
        connectionSettings(connection, HttpMethod.POST, request, option);
        connection.setDoOutput(true);
        connection.setDoInput(true);

        // 设置header
        setRequestHeader(connection, request.getHeaders());

        // 处理请求数据
        HttpRequestBody httpRequestBody = request.getBody();
        httpRequestBody.prepare(connection, request);
        byte[] data = httpRequestBody.serializeBody(request.getCharset());

        connection.connect();
        IOUtils.write(data, connection.getOutputStream());

        // 构造response
        try {
            return generateHttpResponse(connection, responseBodyClass);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            connection.disconnect();
        }
    }

    /**
     * 发送PUT请求
     *
     * @param request
     * @param responseBodyClass
     * @param <T>
     * @return
     * @throws IOException
     */
    public <T extends HttpResponseBodyProcessor> HttpResponse<T> put(HttpRequest request, Class<T> responseBodyClass) throws IOException {

        URL url = new URL(request.getUrlFull());
        HttpURLConnection connection;
        if(Toolkit.exist(option.getProxy())) {
            connection = (HttpURLConnection) url.openConnection(option.getProxy());
        } else {
            connection = (HttpURLConnection) url.openConnection();
        }
        connectionSettings(connection, HttpMethod.PUT, request, option);
        connection.setDoOutput(true);
        connection.setDoInput(true);

        // 设置header
        setRequestHeader(connection, request.getHeaders());

        // 处理请求数据
        HttpRequestBody httpRequestBody = request.getBody();
        httpRequestBody.prepare(connection, request);
        byte[] data = httpRequestBody.serializeBody(request.getCharset());

        connection.connect();
        IOUtils.write(data, connection.getOutputStream());

        // 构造response
        try {
            return generateHttpResponse(connection, responseBodyClass);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            connection.disconnect();
        }
    }

    /**
     * 发送DELETE请求
     *
     * @param request
     * @param responseBodyClass
     * @param <T>
     * @return
     * @throws IOException
     */
    public <T extends HttpResponseBodyProcessor> HttpResponse<T> delete(HttpRequest request, Class<T> responseBodyClass) throws IOException {

        URL url = new URL(request.getUrlFull());
        HttpURLConnection connection;
        if(Toolkit.exist(option.getProxy())) {
            connection = (HttpURLConnection) url.openConnection(option.getProxy());
        } else {
            connection = (HttpURLConnection) url.openConnection();
        }
        connectionSettings(connection, HttpMethod.DELETE, request, option);
        connection.setDoOutput(true);
        connection.setDoInput(true);

        // 设置header
        setRequestHeader(connection, request.getHeaders());

        // 处理请求数据
        HttpRequestBody httpRequestBody = request.getBody();
        httpRequestBody.prepare(connection, request);
        byte[] data = httpRequestBody.serializeBody(request.getCharset());

        connection.connect();
        IOUtils.write(data, connection.getOutputStream());

        // 构造response
        try {
            return generateHttpResponse(connection, responseBodyClass);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            connection.disconnect();
        }
    }

    /**
     * 设置请求头
     *
     * @param connection
     * @param headers
     */
    private void setRequestHeader(HttpURLConnection connection, Set<HttpHeader> headers) {
        for (HttpHeader httpRequestHeader : headers) {
            connection.setRequestProperty(httpRequestHeader.getHeaderName(), httpRequestHeader.getValue());
        }
    }

    private void connectionSettings(HttpURLConnection connection, HttpMethod method, HttpRequest request, Option option) throws ProtocolException {
        connection.setRequestMethod(method.name());
        connection.setRequestProperty("charset", request.getCharset().displayName());
        connection.setReadTimeout(option.getConnectTimeout());
        connection.setConnectTimeout(option.getReadTimeout());
        connection.setUseCaches(false);
    }

    private <T extends HttpResponseBodyProcessor> HttpResponse<T> generateHttpResponse(HttpURLConnection connection, Class<T> responseBodyProcessorClass) throws Exception {
        HttpResponse<T> response = new HttpResponse<>();
        Constructor<T> constructor = responseBodyProcessorClass.getConstructor();
        T responseBodyProcessor = constructor.newInstance();
        responseBodyProcessor.parseBody(connection);
        response.setBodyProcessor(responseBodyProcessor);
        response.setHttpStatus(connection.getResponseCode());
        response.parseHeaders(connection.getHeaderFields());
        return response;
    }

}
