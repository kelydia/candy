package com.cjgod.candy.morning.interceptor;

/**
 * Created by lichunjiang on 2016/12/16.
 */

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.cjgod.candy.common.exception.*;
import com.cjgod.candy.common.util.DateFormatUtil;
import com.cjgod.candy.enums.ResultStatusEnum;
import com.cjgod.candy.helper.SpringContextHelper;
import com.cjgod.candy.logger.InfoLoggerUtil;
import com.cjgod.candy.logger.RequestLoggerUtil;
import com.cjgod.candy.logger.ResponseLoggerUtil;
import com.cjgod.candy.model.BaseModel;
import com.cjgod.candy.model.JsonResult;
import com.cjgod.candy.morning.common.annotation.CheckSessionTimeout;
import com.cjgod.candy.morning.common.annotation.RequiredInterceptor;
import com.cjgod.candy.morning.common.auth.UserInfoCommon;
import com.cjgod.candy.morning.common.constant.Constant;
import com.cjgod.candy.morning.common.tool.MessageTool;
import com.cjgod.candy.morning.common.util.MapperUtil;
import com.cjgod.candy.morning.common.util.RequestUtil;
import com.cjgod.candy.morning.common.web.WebContext;
import com.cjgod.candy.morning.model.PlatformOperationRecordModel;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSONObject;


public class MyControllerInterceptor implements MethodInterceptor {

    protected static final String REQUEST_PARAMETER = "REQUEST";
    protected static final String JSON_PARAMETER = "JSON";
    protected static final String MODEL_PARAMETER = "MODEL";

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {

        RequiredInterceptor ann = AnnotationUtils.findAnnotation(invocation.getMethod(), RequiredInterceptor.class);
        if (ann == null || !ann.required())
            return invocation.proceed();

        Method method = invocation.getMethod();
        HttpServletRequest request = WebContext.getRequest();

        Integer uid = null;

        // 检查session超时
        if (AnnotationUtils.findAnnotation(method, CheckSessionTimeout.class) != null
                || AnnotationUtils.findAnnotation(method.getDeclaringClass(), CheckSessionTimeout.class) != null) {

            try {

                uid = UserInfoCommon.getPlatformUid();

            } catch (Exception e) {
                // session 超时
                JsonResult errResultJson = new JsonResult();
                errResultJson.ret = ResultStatusEnum.clear.name();
                errResultJson.msg = Constant.TIME_OUT;
                if (String.class.equals(method.getReturnType()))
                    // 如果返回结果是ModelAndView, 跳转至异常信息页面
                    return "login";
                else if (JsonResult.class.equals(method.getReturnType()))
                    return errResultJson;
            }
        }

        Object rs = null;
        // 参数
        JSONObject jsonParams = getJsonParams(invocation);
        // ip
        String ip = RequestUtil.getIpFrom(request);
        // log sequence
        String logSeqNo = DateFormatUtil.sequenceNo();

        try {
            if (uid == null)
                uid = UserInfoCommon.getPlatformUid();
            // 给log中设置uid
            jsonParams.put("plf_uid", uid);
        } catch (CandyRuntimeException e) {
        }

        // logging request
        RequestLoggerUtil.i(ip + " " + logSeqNo + " " + MapperUtil.write(jsonParams));

        try {
            rs = invocation.proceed();

            return rs;
        } catch (Throwable e) {
            String msgId = null;
            Map<String, Object> msgMap = null;
            String[] msgArray = null;
            String errorMsg = "";
            if (e instanceof CandyRuntimeException) {
                if (e instanceof CandyParameterException && !StringUtils.isBlank(e.getMessage())) {
                    msgId = "paramsError." + e.getMessage();
                } else if (e instanceof CandyBusinessException && !StringUtils.isBlank(e.getMessage())) {
                    msgId = "bizError." + e.getMessage();
                } else if (e instanceof CandySystemException && !StringUtils.isBlank(e.getMessage())) {
                    msgId = "sysError." + e.getMessage();
                } else if (e instanceof CandyAuthenticationException && !StringUtils.isBlank(e.getMessage())) {
                    msgId = "authError." + e.getMessage();
                }

                Map<String, ?> errParams = ((CandyRuntimeException) e).getParams();
                String[] errParamsArray = ((CandyRuntimeException) e).getParamsArray();
                if (errParams != null)
                    msgMap = new HashMap<String, Object>(((CandyRuntimeException) e).getParams());
                if (errParamsArray != null)
                    msgArray = ((CandyRuntimeException) e).getParamsArray();
            }

            if (StringUtils.isBlank(msgId)) {
                msgId = "systemError";
            }

            MessageTool messageTool = SpringContextHelper.getBean(MessageTool.class);
            if (msgMap != null)
                errorMsg = messageTool.getMessage(msgId, msgMap);

            if (msgArray != null)
                errorMsg = messageTool.getMessage(msgId, msgArray);

            if(msgArray==null&&msgMap==null)
                errorMsg = messageTool.getMessage(msgId);

            JsonResult errResultJson = new JsonResult();
            errResultJson.ret = ResultStatusEnum.failed.name();
            errResultJson.msg = errorMsg;
            try {
                if (String.class.equals(method.getReturnType())) {
                    // 如果返回结果是ModelAndView, 跳转至异常信息页面
                    request.setAttribute("errorMsg", errorMsg);
                    return "error";
                }

                if (!JsonResult.class.equals(method.getReturnType())) {
                    // 如果返回结果不是JsonResult类型, 直接跳转至异常页
                    throw e;
                }

                rs = MapperUtil.convert(errResultJson, method.getReturnType());

                return rs;
            } finally {
                // logging error
                InfoLoggerUtil.e(errorMsg, e);

                e.printStackTrace();
            }
        } finally {
            // response log
            JSONObject jsonResult = null;
            try {
                jsonResult = getJsonResult(rs);
                // logging
                ResponseLoggerUtil.i(ip + " " + logSeqNo + " " + MapperUtil.write(jsonResult));
            } catch (Exception e) {
                // logging
                ResponseLoggerUtil.i(ip + " " + logSeqNo + "ModelAndView" + rs.toString());
            }


            // 用户uid
            try {
                if (uid == null)
                    uid = UserInfoCommon.getPlatformUid();
            } catch (CandyRuntimeException e) {
            }

            // 记录用户操作日志
            if (rs != null && uid != null) {
                // 设置uid
                jsonParams.put("plf_uid", uid);

                PlatformOperationRecordModel mo = new PlatformOperationRecordModel();
                mo.setAction(method.getDeclaringClass().getName() + "." + method.getName() );
                mo.setDescription(ann.desc()); // 方法描述
                mo.setIp(ip);
                mo.setParams(MapperUtil.write(jsonParams));
                mo.setUid(new BigDecimal(uid));
                mo.setOptTime(DateFormatUtil.dateToString(DateFormatUtil.now(), DateFormatUtil.YYMMDDHHMMSSSSS));

                InfoLoggerUtil.i(MapperUtil.write(mo));
                mo.setParams(null);

                //ProducerCommon.send(CandyContext.sysProp("kafka.topic.platformOperation"), MapperUtil.write(mo));
            }
        }
    }

    @SuppressWarnings("unchecked")
    private JSONObject getJsonParams(MethodInvocation invocation) {
        Object args[] = invocation.getArguments();
        JSONObject rt = new JSONObject();
        rt.put(REQUEST_PARAMETER, new JSONObject());
        rt.put(JSON_PARAMETER, new JSONObject());
        rt.put(MODEL_PARAMETER, new JSONObject());

        HttpServletRequest request = WebContext.getRequest();

        Enumeration<String> requestParams = request.getParameterNames();
        if (requestParams != null) {
            JSONObject req = new JSONObject();

            while (requestParams.hasMoreElements()) {
                String key = requestParams.nextElement();
                String value = request.getParameter(key);
                req.put(key, value);
            }
            rt.getJSONObject(REQUEST_PARAMETER).putAll(req);
        }

        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                Object arg = args[i];

                if (arg instanceof BaseModel) {
                    rt.getJSONObject(JSON_PARAMETER).putAll(MapperUtil.writeMap(arg));
                } else if (arg instanceof Model)
                    rt.getJSONObject(MODEL_PARAMETER).putAll(((Model) arg).asMap());
                else if (arg instanceof ModelMap)
                    rt.getJSONObject(MODEL_PARAMETER).putAll((ModelMap) arg);
            }
        }

        return rt;
    }

    private JSONObject getJsonResult(Object result) {
        if (result != null) {
            if (result instanceof ModelAndView) {
                ModelMap modelMap = ((ModelAndView) result).getModelMap();
                return MapperUtil.convert(modelMap, JSONObject.class);
            } else {
                return MapperUtil.convert(result, JSONObject.class);
            }
        }

        return new JSONObject();
    }
}

