package jun.prospring5.ch5;


import org.aopalliance.aop.Advice;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProxyFactoryBeanConfiguration {

    @Bean
    public GrammyGuitarist johnMayer() {
        return new GrammyGuitarist();
    }

    @Bean
    public Advice advice() {
        Advice advice = new AuditAdvice();
        return advice;
    }

    @Bean
    public Advisor advisor(
            @Qualifier("advice") Advice advice) {

        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* sing*(..))");

        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
        advisor.setAdvice(advice);
        advisor.setPointcut(pointcut);

        return advisor;
    }

    @Bean
    public ProxyFactoryBean proxyOne() {

        ProxyFactoryBean bean = new ProxyFactoryBean();
        bean.setTargetName("johnMayer");
        bean.setInterceptorNames("advice");
        bean.setProxyTargetClass(true);

        return bean;
    }

    @Bean
    public ProxyFactoryBean proxyTwo() {

        ProxyFactoryBean bean = new ProxyFactoryBean();
        bean.setTargetName("johnMayer");
        bean.setInterceptorNames("advisor");
        bean.setProxyTargetClass(true);

        return bean;
    }

    @Bean
    public Documentarist documentaristOne(
            @Qualifier("proxyOne") GrammyGuitarist proxy) {

        Documentarist documentarist = new Documentarist();
        documentarist.setGuitarist(proxy);

        return documentarist;
    }

    @Bean
    public Documentarist documentaristTwo(
            @Qualifier("proxyTwo") GrammyGuitarist proxy) {

        Documentarist documentarist = new Documentarist();
        documentarist.setGuitarist(proxy);

        return documentarist;
    }
}
