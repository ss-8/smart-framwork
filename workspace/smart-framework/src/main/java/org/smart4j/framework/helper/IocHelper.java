package org.smart4j.framework.helper;

import java.lang.reflect.Field;
import java.util.Map;

import org.smart4j.framework.annotation.Inject;
import org.smart4j.framework.util.ArrayUtil;
import org.smart4j.framework.util.CollectionUtil;
import org.smart4j.framework.util.ReflectionUtil;

/**
 * 依赖注入助手类
 */
public final class IocHelper {
    static {
        // 获取所有的Bean类雨Bean实例的映射
        Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();
        if (CollectionUtil.isNotEmpty(beanMap)) {
            //遍历Bean Map
            for (Map.Entry<Class<?>, Object> beanEntry : beanMap.entrySet()) {
                Class<?> beanClass = beanEntry.getKey();
                Object beanInstance = beanEntry.getValue();
                // 获取beanClass所有成员变量
                Field[] fields = beanClass.getDeclaredFields();
                if (ArrayUtil.isNotEmpty(fields)) {
                    // 遍历Bean Field
                    for (Field beanField : fields) {
                        // 判断当前beanField是否有Inject注解
                        if (beanField.isAnnotationPresent(Inject.class)) {
                            // 在BEAN_MAP中获取当前beanField对应类型的实例
                            Class<?> cls = beanField.getType();
                            Object beanFieldInstance = beanMap.get(cls);
                            if (beanField != null) {
                                // 通过反射初始化该成员变量的值
                                ReflectionUtil.setField(beanEntry.getValue(), beanField, beanFieldInstance);
                            }
                        }
                    }
                }
            }
        }
    }
}
