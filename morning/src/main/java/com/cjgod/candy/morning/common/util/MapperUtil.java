package com.cjgod.candy.morning.common.util;

/**
 * Created by lichunjiang on 2016/12/16.
 */
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;


public class MapperUtil {

    public static <T> T convert(Object obj, Class<T> clsType) {
        try {
            return JSON.parseObject(JSON.toJSONString(obj), clsType);
        } catch (IllegalArgumentException e) {
        }
        return null;
    }

    public static <T> T read(String jsonString, Class<T> clsType) {
        try {
            return JSON.toJavaObject(JSON.parseObject(jsonString), clsType);
        } catch (IllegalArgumentException e) {
        }
        return null;
    }

    public static <T> List<T> readList(String jsonList, Class<T> clsType) {
        try {
            return JSONArray.parseArray(jsonList, clsType);
        } catch (IllegalArgumentException e) {
        }
        return null;
    }

    public static <T> T readMap(Map<String, ?> map, Class<T> clsType) {
        try {
            return read(JSON.toJSONString(map, true), clsType);
        } catch (IllegalArgumentException e) {
        }
        return null;
    }

    public static String write(Object obj) {
        try {
            return JSON.toJSONString(obj);
        } catch (IllegalArgumentException e) {
        }
        return null;
    }

/*    public static Object convertToBson(Object obj) {
        SerializeConfig config = new SerializeConfig();
        config.put(Date.class, new ObjectSerializer() {
            @Override
            public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType,
                              int features) throws IOException {
                if (object == null) {
                    serializer.out.writeNull();
                    return;
                }
                Date date = (Date) object;
                serializer.out.write(String.format("{\"$date\" : %s}", BigDecimal.valueOf(date.getTime()).toString()));
            }
        });

        return com.mongodb.util.JSON.parse(Document.parse(JSON.toJSONString(obj, config)).toJson());
    }*/

    @SuppressWarnings("unchecked")
    public static <T> Map<String, T> writeMap(Object obj) {
        try {
            return JSON.parseObject(JSON.toJSONString(obj), HashMap.class);
        } catch (IllegalArgumentException e) {
        }
        return null;
    }

    public static Map<?, ?> stringToMap(String jsonString) {
        try {
            return JSON.parseObject(jsonString, HashMap.class);
        } catch (IllegalArgumentException e) {
        }
        return null;
    }
}

