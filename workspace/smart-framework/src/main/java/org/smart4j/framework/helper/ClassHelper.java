package org.smart4j.framework.helper;

import java.util.HashSet;
import java.util.Set;

import org.smart4j.framework.annotation.Controller;
import org.smart4j.framework.annotation.Service;
import org.smart4j.framework.util.ClassUtil;

/**
 * 类操作助手类
 * 
 */
public final class ClassHelper {
    /**
     * 类集合，用于存放所加载的类
     */
    private static final Set<Class<?>> CLASS_SET;
    static {
        String basePackage = ConfigHelper.getAppBasePackage();
        CLASS_SET = ClassUtil.getClassSet(basePackage);
    }

    /**
     * 获取应用基础包中所有类实例
     */
    public static Set<Class<?>> getClassSet() {
        return CLASS_SET;
    }
    
    /**
     * 获取基础包中所有Service类
     */
    public static Set<Class<?>> getServiceClassSet() {
        Set<Class<?>> classSet = new HashSet<>();
        for (Class<?> cls : CLASS_SET) {
            if (cls.isAnnotationPresent(Service.class))
                classSet.add(cls);
        }
        return classSet;
    }

    /**
     * 获取基础包中所有Service类
     */
    public static Set<Class<?>> getControllerClassSet() {
        Set<Class<?>> classSet = new HashSet<>();
        for (Class<?> cls : CLASS_SET) {
            if (cls.isAnnotationPresent(Controller.class))
                classSet.add(cls);
        }
        return classSet;
    }

    /**
     * 获取基础包中所有Bean,
     */
    public static Set<Class<?>> getBeanClassSet() {
        Set<Class<?>> classSet = new HashSet<>();
        classSet.addAll(getControllerClassSet());
        classSet.addAll(getServiceClassSet());
        return classSet;
    }
}
