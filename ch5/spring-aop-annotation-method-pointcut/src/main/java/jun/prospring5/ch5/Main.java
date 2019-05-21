package jun.prospring5.ch5;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;

public class Main {

    public static void main(String[] args) {

        GoodSinger good = new GoodSinger();

        AnnotationMatchingPointcut pointcut =
                AnnotationMatchingPointcut.forMethodAnnotation(
                        AdviceRequired.class);

        Advice advice = new SimpleAdvice();
        Advisor advisor = new DefaultPointcutAdvisor(pointcut, advice);

        ProxyFactory goodProxyFactory = new ProxyFactory();
        goodProxyFactory.addAdvisor(advisor);
        goodProxyFactory.setTarget(good);
        Singer goodProxy = (Singer) goodProxyFactory.getProxy();

        goodProxy.sing();
        goodProxy.dance();
        goodProxy.singDance();
    }
}
