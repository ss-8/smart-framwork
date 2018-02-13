package org.smart4j.framework.helper;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.smart4j.framework.util.ReflectionUtil;

/**
 * Bean助手类
 */
public final class BeanHelper {
    /**
     * Bean Map映射
     */
    private static final Map<Class<?>, Object> BEAN_MAP = new HashMap<>();
    static{
        Set<Class<?>> beanClassSet = ClassHelper.getBeanClassSet();
        for (Class<?> cls : beanClassSet) {
            Object instance = ReflectionUtil.newInstance(cls);
            BEAN_MAP.put(cls, instance);
        }
    }

    /**
     * 获取BeanMap映射
     */
    public static Map<Class<?>, Object> getBeanMap() {
        return BEAN_MAP;
    }

    /**
     * 获取Bean实例
     */
    public static <T> T getBean(Class<T> cls) {
        if (!BEAN_MAP.containsKey(cls))
            throw new RuntimeException("can not get bean from class" + cls);
        return (T) BEAN_MAP.get(cls);
    }
}
