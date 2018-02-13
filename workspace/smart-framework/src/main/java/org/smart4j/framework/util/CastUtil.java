package org.smart4j.framework.util;

/**
 * 类型转换工具类
 */
public final class CastUtil {
    /**
     * 转成String,提供默认值
     */
    public static String castString(Object object, String defaultValue) {
        return object != null ? String.valueOf(object) : defaultValue;
    }

    /**
     * 转成String,默认值为空串
     */
    public static String castString(Object object) {
        return castString(object, "");
    }

    /**
     * 转成double,提供默认值
     */
    public static double castDouble(Object object, double defaultValue) {
        double value = defaultValue;
        if (object != null) {
            String strValue = castString(object);
            if (StringUtil.isNotEmpty(strValue)) {
                try {
                    value = Double.parseDouble(strValue);
                } catch (NumberFormatException e) {
                    value = defaultValue;
                }
            }
        }
        return value;
    }

    /**
     * 转成double,默认值为0
     */
    public static double castDouble(Object object) {
        return castDouble(object, 0);
    }

    /**
     * 转成long,提供默认值
     */
    public static long castLong(Object object, long defaultValue) {
        long value = defaultValue;
        if (object != null) {
            String strValue = castString(object);
            if (StringUtil.isNotEmpty(strValue)) {
                try {
                    value = Long.parseLong(strValue);
                } catch (NumberFormatException e) {
                    value = defaultValue;
                }
            }
        }
        return value;
    }

    /**
     * 转成long,默认值为0
     */
    public static long castLong(Object object) {
        return castLong(object, 0);
    }

    /**
     * 转成int,提供默认值
     */
    public static int castInt(Object object, int defaultValue) {
        int value = defaultValue;
        if (object != null) {
            String strValue = castString(object);
            if (StringUtil.isNotEmpty(strValue)) {
                try {
                    value = Integer.parseInt(strValue);
                } catch (NumberFormatException e) {
                    value = defaultValue;
                }
            }
        }
        return value;
    }

    /**
     * 转成int,默认值为0
     */
    public static int castInt(Object object) {
        return castInt(object, 0);
    }
}
