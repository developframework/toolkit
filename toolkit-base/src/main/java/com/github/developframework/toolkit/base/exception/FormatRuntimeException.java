package com.github.developframework.toolkit.base.exception;

/**
 * 带格式化的运行异常
 * @author qiuzhenhao
 */
public class FormatRuntimeException extends RuntimeException{

    public FormatRuntimeException() {
    }

    public FormatRuntimeException(String message) {
        super(message);
    }

    public FormatRuntimeException(String format, Object... args) {
        super(String.format(format, args));
    }

    public FormatRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public FormatRuntimeException(Throwable cause, String format, Object... args) {
        super(String.format(format, args), cause);
    }
}
