package com.cjgod.candy.common.exception;

import java.util.Map;

/**
 * Created by lichunjiang on 2016/12/16.
 */
public class CandySystemException extends CandyRuntimeException {
    private static final long serialVersionUID = 2812522806209410607L;

    public CandySystemException() {
        super();
    }

    public CandySystemException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public CandySystemException(String msg) {
        super(msg);
    }

    public CandySystemException(Throwable cause) {
        super(cause);
    }

    public CandySystemException(String msg, Map<String, Object> params) {
        super(msg, params);
    }

    public CandySystemException(String msg, String[] paramsArray) {
        super(msg, paramsArray);
    }
}
