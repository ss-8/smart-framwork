package org.smart4j.framework;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.smart4j.framework.bean.Data;
import org.smart4j.framework.bean.RequestHandler;
import org.smart4j.framework.bean.RequestParam;
import org.smart4j.framework.bean.View;
import org.smart4j.framework.helper.BeanHelper;
import org.smart4j.framework.helper.ConfigHelper;
import org.smart4j.framework.helper.ControllerHelper;
import org.smart4j.framework.util.ArrayUtil;
import org.smart4j.framework.util.CodecUtil;
import org.smart4j.framework.util.JsonUtil;
import org.smart4j.framework.util.ReflectionUtil;
import org.smart4j.framework.util.StreamUtil;
import org.smart4j.framework.util.StringUtil;

/**
 * 请求转发器:匹配所有请求URL,HttpServlet类tomcat启动时初始化
 */
@WebServlet(urlPatterns = "/*", loadOnStartup = 0)
public class DispatcherServlet extends HttpServlet {

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        // 初始化相关Helper类
        HelperLoader.init();
        // 获取ServletContext对象:用于注册Servlet
        ServletContext servletContext = servletConfig.getServletContext();
        // 注册处理JSP的Servlet,默认路径是/WEB-INF/view/
        ServletRegistration jspServlet = servletContext.getServletRegistration("jsp");
        jspServlet.addMapping(ConfigHelper.getAppJspPath() + "*");
        // 注册处理静态资源的默认Servlet,默认路径是/asset/
        ServletRegistration defaultServlet = servletContext.getServletRegistration("default");
        defaultServlet.addMapping(ConfigHelper.getAppAssetPath() + "*");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取请求方法和请求路径
        String requestMethod = req.getMethod().toLowerCase();
        String requestPath = req.getPathInfo();
        // 获取请求处理器
        RequestHandler requestHandler = ControllerHelper.getHandler(requestMethod, requestPath);
        if (requestHandler != null) {
            // 获取Controller类和Bean实例
            Class<?> controllerClass = requestHandler.getController();
            Object controllerBean = BeanHelper.getBean(controllerClass);
            // 创建请求参数对象
            //保存post提交的参数
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("request", req);
            paramMap.put("response", resp);
            Enumeration<String> paramNames = req.getParameterNames();
            while (paramNames.hasMoreElements()) {
                String paramName = paramNames.nextElement();
                String paramValue = req.getParameter(paramName);
                paramMap.put(paramName, paramValue);
            }
            // 保存get提交的参数
            String requestBody = CodecUtil.decodeURL(StreamUtil.getString(req.getInputStream()));
            if (StringUtil.isNotEmpty(requestBody)) {
                String[] params = StringUtil.splitString(requestBody, "&");
                if (ArrayUtil.isNotEmpty(params)) {
                    for(String param:params){
                        String[] array = StringUtil.splitString(param, "=");
                        if (ArrayUtil.isNotEmpty(array) && array.length == 2) {
                            String paramName = array[0];
                            String paramValue = array[1];
                            paramMap.put(paramName, paramValue);
                        }
                    }
                }
            }
            RequestParam requestParam = new RequestParam(paramMap);
            // 调用Action方法
            Method actionMethod = requestHandler.getActionMethod();
            Object result = ReflectionUtil.invokeMehod(controllerBean, actionMethod, requestParam);
            // 设置Action返回值
            if (result instanceof View) {
                // 返回JSP页面
                View view = (View) result;
                String path = view.getPath();
                if (StringUtil.isNotEmpty(path)) {
                        Map<String, Object> model = view.getModel();
                        for (Map.Entry<String, Object> entry : model.entrySet()) {
                            req.setAttribute(entry.getKey(), entry.getValue());
                        }
                    // 请求转发，服务端内部跳转，浏览器地址栏不变
                    // TODO:
                    // 如果ConfigHelper.getAppJspPath()是/WEB-INF/view
                    // req.getRequestDispatcher(ConfigHelper.getAppJspPath() +
                    // path).forward(req, resp)修改为
                    // req.getRequestDispatcher(ConfigHelper.getAppJspPath()+"/"+
                    // path).forward(req, resp)就会报错
                        req.getRequestDispatcher(ConfigHelper.getAppJspPath() + path).forward(req, resp);
                }
            } else if (result instanceof Data) {
                // 返回JSON数据
                Data data = (Data) result;
                Object model = data.getModel();
                if (model != null) {
                    resp.setCharacterEncoding("UTF-8");
                    resp.setContentType("application/json");
                    PrintWriter printWriter = resp.getWriter();
                    String json = JsonUtil.toJson(model);
                    printWriter.write(json);
                    printWriter.flush();
                    printWriter.close();
                }
            }
        }
    }



}
