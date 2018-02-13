package proxyDemo.spring.aop;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

public class HelloBeforeAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("HelloBeforeAdvice before");
    }

}
