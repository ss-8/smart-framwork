package org.smart4j.framework.bean;

import java.util.Map;

import org.smart4j.framework.util.CastUtil;

/**
 * 请求参数对象
 */
public class RequestParam {
    private Map<String, Object> paramMap;

    public Map<String, Object> getParamMap() {
        return paramMap;
    }

    public void setParamMap(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
    }

    public RequestParam(Map<String, Object> paramMap) {
        super();
        this.paramMap = paramMap;
    }

    /**
     * 根据参数名获取long型参数
     */
    public long getLong(String paramName) {
        return CastUtil.castLong(paramMap.get(paramName));
    }

}
