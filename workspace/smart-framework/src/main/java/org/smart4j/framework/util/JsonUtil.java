package org.smart4j.framework.util;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 用于JSON串和POJO之间的转换
 */
public final class JsonUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtil.class);
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    /**
     * POJO->JSON串
     */
    public static <T> String toJson(T obj) {
        String json;
        try {
            json = OBJECT_MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            LOGGER.error("convert pojo to json failure", e);
            throw new RuntimeException(e);
        }
        return json;
    }

    /**
     * JSON串->POJO
     */
    public static <T> T fromJson(String json, Class<T> type) {
        T pojo;
        try {
            pojo = OBJECT_MAPPER.readValue(json, type);
        } catch (IOException e) {
            LOGGER.error("convert json to pojo failure", e);
            throw new RuntimeException(e);
        }
        return pojo;
    }

}
