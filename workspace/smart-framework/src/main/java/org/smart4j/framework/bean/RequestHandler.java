package org.smart4j.framework.bean;

import java.lang.reflect.Method;

/**
 * 请求处理器
 * 封装Action方法信息：哪个类的哪个方法
 */
public class RequestHandler {
    /**
     * Controller类
     */
    private Class<?> controller;
    /**
     * Action方法
     */
    private Method actionMethod;

    public Class<?> getController() {
        return controller;
    }

    public void setController(Class<?> controller) {
        this.controller = controller;
    }

    public Method getActionMethod() {
        return actionMethod;
    }

    public void setActionMethod(Method actionMethod) {
        this.actionMethod = actionMethod;
    }

    public RequestHandler(Class<?> controller, Method actionMethod) {
        super();
        this.controller = controller;
        this.actionMethod = actionMethod;
    }

}
