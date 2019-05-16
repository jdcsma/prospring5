package jun.prospring5.ch4;

import org.springframework.aop.framework.ProxyFactory;

public class Main {

    public static void main(String[] args) {

        Agent target = new Agent();

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.addAdvice(new AgentDecorator());
        proxyFactory.setTarget(target);

        Agent proxy = (Agent) proxyFactory.getProxy();

        proxy.speak();
    }
}
