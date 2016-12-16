package com.cjgod.candy.common.exception;

/**
 * Created by lichunjiang on 2016/12/16.
 */
public class CandySessionTimeoutException extends CandyRuntimeException {
    private static final long serialVersionUID = 6410991264231778835L;

    public CandySessionTimeoutException() {
        super();
    }

    public CandySessionTimeoutException(String msg) {
        super(msg);
    }

    public CandySessionTimeoutException(Throwable cause) {
        super(cause);
    }

    public CandySessionTimeoutException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
