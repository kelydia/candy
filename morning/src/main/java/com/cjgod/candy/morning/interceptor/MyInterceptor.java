package com.cjgod.candy.morning.interceptor;

/**
 * Created by lichunjiang on 2016/12/16.
 */

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class MyInterceptor extends HandlerInterceptorAdapter {
    private static final String LOGIN_URL = "login";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
                + "/";

//        HttpSession session = request.getSession(true);
//        // 从session 里面获取用户名的信息
//        Object obj = session.getAttribute(Constant.USER_SESSION);
//        // 判断如果没有取到用户信息，就跳转到登陆页面，提示用户进行登陆
//        if (obj == null || "".equals(obj.toString())) {
//            response.sendRedirect(basePath + LOGIN_URL);
//            return false;
//        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e)
            throws Exception {
    }
}
