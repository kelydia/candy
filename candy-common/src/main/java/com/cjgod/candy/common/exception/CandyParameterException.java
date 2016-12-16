package com.cjgod.candy.common.exception;

import java.util.Map;

/**
 * Created by lichunjiang on 2016/12/16.
 */
public class CandyParameterException extends CandyRuntimeException {
    private static final long serialVersionUID = 560707754236550130L;

    public CandyParameterException() {
        super();
    }

    public CandyParameterException(String msg) {
        super(msg);
    }

    public CandyParameterException(Throwable cause) {
        super(cause);
    }

    public CandyParameterException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public CandyParameterException(String msg, Map<String, ?> params) {
        super(msg, params);
    }

    public CandyParameterException(String msg,String[] paramsArray) {
        super(msg, paramsArray);
    }
}
