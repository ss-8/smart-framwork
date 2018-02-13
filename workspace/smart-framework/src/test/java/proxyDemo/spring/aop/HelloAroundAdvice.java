package proxyDemo.spring.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class HelloAroundAdvice implements MethodInterceptor {

    private void after() {
        System.out.println("around after! ");
    }

    private void before() {
        System.out.println("around before! ");
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        before();
        Object result = invocation.proceed();
        after();
        return result;
    }
}
