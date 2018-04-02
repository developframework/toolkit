package test;

import com.github.developframework.toolkit.http.ToStringHttpResponseBody;
import com.github.developframework.toolkit.http.ToolkitHttpClient;
import com.github.developframework.toolkit.http.request.HttpRequest;
import com.github.developframework.toolkit.http.request.JsonRawHttpRequestBody;
import com.github.developframework.toolkit.http.response.HttpResponse;

import java.io.IOException;

/**
 * @author qiuzhenhao
 */
public class Main {

    public static void main(String[] args) {
        ToolkitHttpClient client = new ToolkitHttpClient();
        HttpRequest httpRequest = new HttpRequest("http://localhost:8080/upjson");
        httpRequest.addUrlParameter("key", "aaa");
        httpRequest.setBody(new JsonRawHttpRequestBody("{\"id\": 1, \"name\": \"zhangsan\"}"));
        try {
            HttpResponse<ToStringHttpResponseBody> response = client.put(httpRequest, ToStringHttpResponseBody.class);
            String bodyContent = response.getBody().getBodyContent();
            System.out.println(response.getHttpStatus());
            System.out.println(bodyContent);
            response.getHeaders().forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
