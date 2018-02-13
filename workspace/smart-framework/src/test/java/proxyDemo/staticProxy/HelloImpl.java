package proxyDemo.staticProxy;

public class HelloImpl implements Hello {

    @Override
    public void say(String content) {
        System.out.println("hello! " + content);
        // throw new RuntimeException("故意抛出的运行时异常");
    }

    public void goodMoning(String name) {
        System.out.println("goodMorning! " + name);
    }

    public void goodNight(String name) {
        System.out.println("goodNight! " + name);
    }

}
