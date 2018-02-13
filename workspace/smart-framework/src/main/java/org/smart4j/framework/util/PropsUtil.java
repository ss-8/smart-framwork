package org.smart4j.framework.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class PropsUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(PropsUtil.class);

    /**
     * 加载配置文件
     */
    public static Properties loadProperties(String fileName) {
        Properties props = null;
        InputStream is = null;
        try {
            is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
            if (is == null)
                throw new FileNotFoundException(fileName + "is not found");
            props = new Properties();
            props.load(is);
        } catch (IOException e) {
            LOGGER.error("load properties file failure", e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    LOGGER.error("close inputStream failure", e);
                }
            }
        }
        return props;
    }

    /**
     * 获取配置文件中属性值(可指定默认值)
     */
    public static String getValue(Properties props, String key, String defaultValue) {
        String value = defaultValue;
        if (props.containsKey(key))
            value = props.getProperty(key);
        return value;
    }

    /**
     * 获取配置文件，默认值为空串
     */
    public static String getValue(Properties props, String key) {
        return getValue(props, key, "");
    }


}
