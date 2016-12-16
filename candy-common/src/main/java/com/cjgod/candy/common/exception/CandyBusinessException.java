package com.cjgod.candy.common.exception;

import java.util.Map;

/**
 * Created by lichunjiang on 2016/12/16.
 */
public class CandyBusinessException extends CandyRuntimeException {

    private static final long serialVersionUID = -2572913773530532282L;

    public CandyBusinessException() {
        super();
    }

    public CandyBusinessException(String msg) {
        super(msg);
    }

    public CandyBusinessException(Throwable cause) {
        super(cause);
    }

    public CandyBusinessException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public CandyBusinessException(String msg, Map<String, Object> params) {
        super(msg, params);
    }

    public CandyBusinessException(String msg, String[] paramsArray) {
        super(msg, paramsArray);
    }
}
