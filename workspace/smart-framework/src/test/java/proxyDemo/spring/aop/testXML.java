package proxyDemo.spring.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import proxyDemo.staticProxy.Hello;
import proxyDemo.staticProxy.HelloImpl;

public class testXML {

    public static void main(String[] args) {
        @SuppressWarnings("resource")
        // 获取spring context
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        Hello hello = (HelloImpl) context.getBean("helloProxy2");// 强转成目标接口
        HelloImpl helloImpl = (HelloImpl) context.getBean("helloProxy2");// 强转成目标类
        hello.say("xml方式配置代理对象");
        helloImpl.say("xml方式配置代理对象");
        Apology apology = (Apology) helloImpl;
        apology.saySorry("动态引入");
    }

}
