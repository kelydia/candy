package com.cjgod.candy.morning.common.context;

/**
 * Created by lichunjiang on 2016/12/16.
 */

import java.util.Map;
import com.cjgod.candy.morning.common.util.LoadPropertiesUtil;
import org.apache.commons.lang.BooleanUtils;

public class CandyContext {
    private static String              SYSTEM_CONF_PATH = "conf/system-config.properties";
    private static String              BIZ_CONF_PATH    = "conf/biz-config.properties";

    // 系统相关配置map
    private static Map<String, String> sysMap           = LoadPropertiesUtil.load(SYSTEM_CONF_PATH);
    // 业务相关配置map
    private static Map<String, String> bizMap           = LoadPropertiesUtil.load(BIZ_CONF_PATH);

    public static String sysProp(String key) {
        return sysMap.get(key);
    }

    public static String bizProp(String key) {
        return bizMap.get(key);
    }

    public static boolean isDebug() {
        return BooleanUtils.toBoolean(sysMap.get("system.debug"));
    }
}

