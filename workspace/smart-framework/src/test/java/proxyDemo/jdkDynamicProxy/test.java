package proxyDemo.jdkDynamicProxy;

import proxyDemo.staticProxy.Hello;
import proxyDemo.staticProxy.HelloImpl;

public class test {
    public static void main(String[] args) {
        Hello hello = new HelloImpl();
        // 创建hello实现类的代理
        Hello helloProxy = new JDKDynamicProxy(hello).getProxy();
        helloProxy.say("tom");
    }
}
