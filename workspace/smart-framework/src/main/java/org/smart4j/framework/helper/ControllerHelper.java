package org.smart4j.framework.helper;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.smart4j.framework.annotation.Action;
import org.smart4j.framework.bean.Request;
import org.smart4j.framework.bean.RequestHandler;
import org.smart4j.framework.util.ArrayUtil;
import org.smart4j.framework.util.CollectionUtil;

/**
 * 控制器助手类
 */
public final class ControllerHelper {
    /**
     * 用于存放请求和处理器的映射关系
     */
    private static final Map<Request, RequestHandler> ACTION_MAP = new HashMap<>();
    static {
        // 获取所有的Controller
        Set<Class<?>> classSet = ClassHelper.getControllerClassSet();
        if (CollectionUtil.isNotEmpty(classSet)) {
            // 遍历Controller类
            for (Class<?> controllerClass : classSet) {
                // 获取类中方法
                Method[] methods = controllerClass.getDeclaredMethods();
                if (ArrayUtil.isNotEmpty(methods)) {
                    // 遍历类中方法，选择Action方法
                    for (Method method : methods) {
                        if (method.isAnnotationPresent(Action.class)) {
                            // 从Action注解中获取URL映射规则
                            Action action = method.getAnnotation(Action.class);
                            String mapping = action.value();
                            // 验证URL映射规则
                            if (mapping.matches("\\w+:/\\w*")) {
                                String[] array = mapping.split(":");
                                if (ArrayUtil.isNotEmpty(array) && array.length == 2) {
                                    // 获取请求方法和请求路径
                                    String requestMethod = array[0];
                                    String requestPath = array[1];
                                    Request request = new Request(requestMethod, requestPath);
                                    RequestHandler handler = new RequestHandler(controllerClass, method);
                                    ACTION_MAP.put(request, handler);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * 根据请求获得对应的处理器
     */
    public static RequestHandler getHandler(String requestMethod,String requestPath){
        Request request=new Request(requestMethod, requestPath);
        RequestHandler requestHandler= ACTION_MAP.get(request);
        if (requestHandler == null) {
            throw new RuntimeException("URL不正确：" + requestPath);
        }
        return requestHandler;
    }
}
