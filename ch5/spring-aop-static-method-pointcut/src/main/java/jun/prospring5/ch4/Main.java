package jun.prospring5.ch4;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Advisor;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

public class Main {

    public static void main(String[] args) {

        GoodGuitarist good = new GoodGuitarist();
        GreatGuitarist great = new GreatGuitarist();

        Pointcut pointcut = new SimpleStaticPointcut();
        Advice advice = new SimpleAdvice();
        Advisor advisor = new DefaultPointcutAdvisor(pointcut, advice);

        ProxyFactory goodProxyFactory = new ProxyFactory();
        goodProxyFactory.addAdvisor(advisor);
        goodProxyFactory.setTarget(good);
        Singer goodProxy = (Singer) goodProxyFactory.getProxy();

        ProxyFactory greatProxyFactory = new ProxyFactory();
        greatProxyFactory.addAdvisor(advisor);
        greatProxyFactory.setTarget(great);
        Singer greatProxy = (Singer) greatProxyFactory.getProxy();

        // Add all advices (filter by ClassFilter in SimpleStaticPointcut)
        // into methodCache in getInterceptorsAndDynamicInterceptionAdvice.
        goodProxy.sing();
        // Get all advices (find it by MethodCacheKey with method)
        // from methodCache in getInterceptorsAndDynamicInterceptionAdvice
        goodProxy.sing();

        greatProxy.sing();
    }
}
