package com.cjgod.candy.morning.common.web;

import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpRequest;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

/**
 * Created by lichunjiang on 2016/12/19.
 */
public class SessionFilter implements Filter {

    public static String sessionKeyName(String sessionId) {
        return "usr_session#" + sessionId;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {

        HttpSession session = ((HttpServletRequest) request).getSession(false);


        WebContext.requestLocal.set((HttpServletRequest)request);
        WebContext.responseLocal.set((HttpServletResponse) response);

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
            }
        }

    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(context);
    }
}
