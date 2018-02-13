package org.smart4j.framework.bean;

/**
 * 返回的数据对象
 */
public class Data {
    /**
     * 模型数据
     */
    private Object model;

    public Object getModel() {
        return model;
    }

    public Data(Object model) {
        super();
        this.model = model;
    }
}
