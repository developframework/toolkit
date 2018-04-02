package com.github.developframework.toolkit.http.response;

import com.github.developframework.toolkit.http.HttpHeader;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Http响应体
 *
 * @author qiuzhenhao
 */
@Getter
public class HttpResponse<BODY extends HttpResponseBody> {

    @Setter
    private int httpStatus;
    @Setter
    private BODY body;

    private Set<HttpHeader> headers;

    public void parseHeaders(Map<String, List<String>> headerFields) {
        this.headers = new HashSet<>();
        for (Map.Entry<String, List<String>> headerFieldsEntry : headerFields.entrySet()) {
            final String headerName = headerFieldsEntry.getKey();
            final List<String> value = headerFieldsEntry.getValue();
            final String[] valueStringArray = value.toArray(new String[value.size()]);
            headers.add(new HttpHeader(headerName, String.join(";", valueStringArray)));
        }
    }

}
