package proxyDemo.staticProxy;

public class HelloProxy implements Hello {

    private Hello hello;

    public HelloProxy() {
        this.hello = new HelloImpl();
    }

    @Override
    public void say(String content) {
        before();
        hello.say(content);
        after();
    }

    private void after() {
        System.out.println("after! ");
    }

    private void before() {
        System.out.println("before! ");
    }

}
