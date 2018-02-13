package proxyDemo.spring.aop;

import org.springframework.aop.framework.ProxyFactory;

import proxyDemo.staticProxy.Hello;
import proxyDemo.staticProxy.HelloImpl;

public class test {
    public static void main(String[] args) {
        ProxyFactory proxyFactory = new ProxyFactory();// 创建代理工厂
        proxyFactory.setTarget(new HelloImpl());// 射入目标类对象
        proxyFactory.addAdvice(new HelloBeforeAdvice());// 添加前置增强
        proxyFactory.addAdvice(new HelloAfterReturningAdvice());// 添加后置增强
        proxyFactory.addAdvice(new HelloAroundAdvice());// 添加环绕增强
        proxyFactory.addAdvice(new HelloThrowAdvice());// 添加抛出增强
        Hello helloProxy = (Hello) proxyFactory.getProxy();// 从代理工厂获取代理
        helloProxy.say("spring aop");
    }

}
