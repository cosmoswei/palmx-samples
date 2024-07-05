package me.wei.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JsonUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    // 静态代码块，用于配置ObjectMapper
    static {
        // 可以添加更多的配置项
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    /**
     * 对象序列化成JSON字符串。
     *
     * @param object 需要序列化的对象
     * @return JSON格式的字符串
     */
    public static String toJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * JSON字符串反序列化成对象。
     *
     * @param json  需要反序列化的JSON字符串
     * @param clazz 目标对象的Class类型
     * @param <T>   目标对象的类型
     * @return 反序列化后的对象
     */
    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}