package org.smart4j.framework.helper;

import java.util.Properties;

import org.smart4j.framework.ConfigConstant;
import org.smart4j.framework.util.PropsUtil;

/**
 * 配置文件助手类
 */
@SuppressWarnings("unused")
public final class ConfigHelper {
    public static final Properties CONFIG_PROPS = PropsUtil.loadProperties(ConfigConstant.CONFIG_FILE);

    /**
     * 获取JDBC驱动
     */

    public static String getJdbcDriver() {
        return PropsUtil.getValue(CONFIG_PROPS, ConfigConstant.JDBC_DRIVER);
    }
    /**
     * 获取JDBC URL
     */
    public static String getJdbcUrl() {
        return PropsUtil.getValue(CONFIG_PROPS, ConfigConstant.JDBC_URL);
    }

    /**
     * 获取JDBC 用户名
     */
    public static String getJdbcUserName() {
        return PropsUtil.getValue(CONFIG_PROPS, ConfigConstant.JDBC_USERNAME);
    }

    /**
     * 获取JDBC 密码
     */
    public static String getJdbcPassword() {
        return PropsUtil.getValue(CONFIG_PROPS, ConfigConstant.JDBC_PASSWORD);
    }

    /**
     * 获取基础包名
     */
    public static String getAppBasePackage() {
        return PropsUtil.getValue(CONFIG_PROPS, ConfigConstant.APP_BASE_PACKAGE);
    }

    /**
     * 获取JSP路径
     */
    public static String getAppJspPath() {
        return PropsUtil.getValue(CONFIG_PROPS, ConfigConstant.APP_JSP_PAHT, "/WEB-INF/view/");
    }

    /**
     * 获取静态资源路径
     */
    public static String getAppAssetPath() {
        return PropsUtil.getValue(CONFIG_PROPS, ConfigConstant.APP_ASSET_PATH, "/asset/");
    }
}
