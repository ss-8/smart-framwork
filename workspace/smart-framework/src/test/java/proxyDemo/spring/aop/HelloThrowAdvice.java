package proxyDemo.spring.aop;

import java.lang.reflect.Method;

import org.springframework.aop.ThrowsAdvice;

public class HelloThrowAdvice implements ThrowsAdvice {
    public void afterThrowing(Method method, Object[] args, Object target, Exception e) {
        System.out.println("----------Throw Exception--------");
        System.out.println("Target Class:" + target.getClass());
        System.out.println("Method Name:" + method.getName());
        System.out.println("Exception Message:" + e.getMessage());
        System.out.println("---------------------------------");

    }
}
