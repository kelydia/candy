package com.cjgod.candy.common.exception;

/**
 * Created by lichunjiang on 2016/12/16.
 */
import java.util.Map;

public class CandyRuntimeException extends RuntimeException {
    private static final long serialVersionUID = 6273866308632864665L;

    public CandyRuntimeException() {
        super();
    }

    public CandyRuntimeException(String msg) {
        super(msg);
    }

    public CandyRuntimeException(Throwable cause) {
        super(cause);
    }

    public CandyRuntimeException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public CandyRuntimeException(String msg, Map<String, ?> params) {
        super(msg);
        this.params = params;
    }

    public CandyRuntimeException(String msg, String[] paramsArray) {
        super(msg);
        this.paramsArray = paramsArray;
    }

    private Map<String, ?> params = null;
    private String[] paramsArray = null;


    public Map<String, ?> getParams() {
        return params;
    }

    public String[] getParamsArray() {
        return paramsArray;
    }

}

