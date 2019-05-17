package jun.prospring5.ch4;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;

public class Main {

    public static void main(String[] args) {

        GoodSinger good = new GoodSinger();

        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedName("sing");

        Advice advice = new SimpleAdvice();
        Advisor advisor = new DefaultPointcutAdvisor(pointcut, advice);

        ProxyFactory goodProxyFactory = new ProxyFactory();
        goodProxyFactory.addAdvisor(advisor);
        goodProxyFactory.setTarget(good);
        Singer goodProxy = (Singer) goodProxyFactory.getProxy();

        goodProxy.sing();
        goodProxy.dance();
    }
}
