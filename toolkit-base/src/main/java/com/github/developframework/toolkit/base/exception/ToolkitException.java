package com.github.developframework.toolkit.base.exception;

/**
 * @author qiuzhenhao
 */
public class ToolkitException extends FormatRuntimeException{

    public ToolkitException(String message) {
        super(message);
    }

    public ToolkitException(String format, Object args) {
        super(format, args);
    }
}
