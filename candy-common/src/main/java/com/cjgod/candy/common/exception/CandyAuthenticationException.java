package com.cjgod.candy.common.exception;

import java.util.Map;

/**
 * Created by lichunjiang on 2016/12/16.
 */
public class CandyAuthenticationException extends CandyRuntimeException {
    private static final long serialVersionUID = -8228578226716930463L;

    public CandyAuthenticationException() {
        super();
    }

    public CandyAuthenticationException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public CandyAuthenticationException(String msg) {
        super(msg);
    }

    public CandyAuthenticationException(Throwable cause) {
        super(cause);
    }

    public CandyAuthenticationException(String msg, Map<String, Object> params) {
        super(msg, params);
    }

    public CandyAuthenticationException(String msg, String[] paramsArray) {
        super(msg, paramsArray);
    }
}