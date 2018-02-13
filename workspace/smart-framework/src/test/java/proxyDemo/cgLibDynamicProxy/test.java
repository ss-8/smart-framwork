package proxyDemo.cgLibDynamicProxy;

import proxyDemo.staticProxy.Hello;
import proxyDemo.staticProxy.HelloImpl;

public class test {
    public static void main(String[] args) {
        Hello helloProxy = CGLibDynamicProxy.getInstance().getProxy(HelloImpl.class);
        helloProxy.say("tom");
    }
}
