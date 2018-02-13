package proxyDemo.staticProxy;

public class test {
    public static void main(String[] args) {
        Hello helloProxy = new HelloProxy();
        helloProxy.say("tom");
    }
}
