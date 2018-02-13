package proxyDemo.spring.aop;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;

public class HelloIntroAdvice extends DelegatingIntroductionInterceptor implements Apology {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        return super.invoke(invocation);
    }

    @Override
    public void saySorry(String name) {
        System.out.println("sorry! " + name);
    }

}
