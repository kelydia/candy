package com.cjgod.candy.morning.common.web;

/**
 * Created by lichunjiang on 2016/12/16.
 */
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebContext {
    static ThreadLocal<HttpServletRequest>  requestLocal  = new ThreadLocal<HttpServletRequest>();
    static ThreadLocal<HttpServletResponse> responseLocal = new ThreadLocal<HttpServletResponse>();

    public static HttpServletRequest getRequest() {
        return requestLocal.get();
    }

    public static HttpServletResponse getResponse() {
        return responseLocal.get();
    }
}
