package com.cjgod.candy.morning.common.util;

/**
 * Created by lichunjiang on 2016/12/16.
 */

import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.cjgod.candy.morning.common.context.CandyContext;
import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONObject;

public class RequestUtil {

    // ip access白名单
    private static final Set<String> IP_WHITE_LIST = new HashSet<>();
    static {
        String whiteList = CandyContext.sysProp("ip.white.list");
        String ipArr[] = null;
        if (!StringUtils.isBlank(whiteList) && (ipArr = whiteList.split(",")).length > 0) {
            for (String ip : ipArr) {
                if (!StringUtils.isBlank(ip))
                    IP_WHITE_LIST.add(ip);
            }
        }
    }

    public static boolean checkAccessable(HttpServletRequest request) {
        if (IP_WHITE_LIST.isEmpty()) {
            return true;
        }

        if (IP_WHITE_LIST.contains(getIpFrom(request))) {
            return true;
        }

        return false;
    }

    /**
     * 获取ip地址
     *
     * @param request
     * @return
     */
    public static String getIpFrom(HttpServletRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Servlet request expect non-null.");
        }

        String ip = request.getHeader("x-forwarded-for");
        if ((ip == null) || (ip.length() == 0) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if ((ip == null) || (ip.length() == 0) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if ((ip == null) || (ip.length() == 0) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        if (StringUtils.isBlank(ip)) {
            return "unknown";
        }

        return ip;
    }

    //    public static JSONObject contentOfRequest(RedisSessionRequest request) {
    //        try {
    //            return JSON.parseObject(request.getContent());
    //        } catch (IOException e) {
    //            return new JSONObject();
    //        }
    //    }

    public static JSONObject attributesOfRequest(HttpServletRequest request) {
        JSONObject ret = new JSONObject();

        @SuppressWarnings("unchecked")
        Enumeration<String> attrNames = request.getAttributeNames();
        while (attrNames.hasMoreElements()) {
            String k = attrNames.nextElement();
            Object v = request.getAttribute(k);
            ret.put(k, v);
        }
        return ret;
    }
}
