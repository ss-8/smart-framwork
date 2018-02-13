package org.smart4j.framework.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 反射工具类：通过类型实例化对象
 */
public final class ReflectionUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReflectionUtil.class);
    /**
     * 创建实例
     */
    public static Object newInstance(Class<?> cls) {
        Object instance = null;
        try {
            instance = cls.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            LOGGER.error("new instance failure",e);
            throw new RuntimeException(e);
        }
        return instance;
    }

    /**
     * 对象object调用方法method(args)
     */
    public static Object invokeMehod(Object object, Method method, Object... args) {
        Object result;
        method.setAccessible(true);
        try {
            result = method.invoke(object, args);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            LOGGER.error("new instance failure", e);
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * 设置成员变量的值
     */
    public static void setField(Object object, Field field, Object value) {
        field.setAccessible(true);
        try {
            field.set(object, value);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            LOGGER.error("set field failure", e);
            throw new RuntimeException(e);
        }
    }

}
