package org.smart4j.framework.proxy;

/**
 * 代理接口
 */
public interface Proxy {
    /**
     * 执行链式代理
     * 可以将多个代理通过一条链子串起来，按照添加顺序顺序执行
     */
    Object doProxy(ProxyChain proxyChain) throws Throwable;
}
