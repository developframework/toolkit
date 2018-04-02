package com.github.developframework.toolkit.http.request;

import java.nio.charset.Charset;

/**
 * Json富文本Body
 * @author qiuzhenhao
 */
public class TextRawHttpRequestBody extends RawHttpRequestBody {

    private String text;

    public TextRawHttpRequestBody(String text) {
        this.text = text;
    }

    @Override
    protected String getContentType(Charset charset) {
        return "text/plain;charset=" + charset.displayName();
    }

    @Override
    public byte[] serializeBody(Charset charset) {
        return text.getBytes(charset);
    }
}
