package jun.prospring5.ch5;

import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.NameMatchMethodPointcutAdvisor;

public class Main {

    public static void main(String[] args) {

        GoodSinger good = new GoodSinger();

        NameMatchMethodPointcutAdvisor advisor = new NameMatchMethodPointcutAdvisor();
        advisor.setMappedName("sing");
        advisor.setAdvice(new SimpleAdvice());

        ProxyFactory goodProxyFactory = new ProxyFactory();
        goodProxyFactory.addAdvisor(advisor);
        goodProxyFactory.setTarget(good);
        Singer goodProxy = (Singer) goodProxyFactory.getProxy();

        goodProxy.sing();
        goodProxy.dance();
    }
}
