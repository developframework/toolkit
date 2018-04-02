package com.github.developframework.toolkit.http.request;

import java.nio.charset.Charset;

/**
 * Json富文本Body
 * @author qiuzhenhao
 */
public class JsonRawHttpRequestBody extends RawHttpRequestBody {

    private String json;

    public JsonRawHttpRequestBody(String json) {
        this.json = json;
    }

    @Override
    protected String getContentType(Charset charset) {
        return "application/json;charset=" + charset.displayName();
    }

    @Override
    public byte[] serializeBody(Charset charset) {
        return json.getBytes(charset);
    }
}
