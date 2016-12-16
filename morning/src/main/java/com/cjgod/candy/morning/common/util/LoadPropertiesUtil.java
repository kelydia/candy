package com.cjgod.candy.morning.common.util;

/**
 * Created by lichunjiang on 2016/12/16.
 */
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public class LoadPropertiesUtil {

    public static Map<String, String> load(String path) {
        Resource resource = new ClassPathResource(path);
        EncodedResource encRes = new EncodedResource(resource, "UTF-8");
        Map<String, String> map = new HashMap<String, String>();
        try {
            Properties props = PropertiesLoaderUtils.loadProperties(encRes);
            for (Object key : props.keySet()) {
                String keyStr = key.toString();
                String value = props.getProperty(keyStr);
                map.put(keyStr, value);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }
}
