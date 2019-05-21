package jun.prospring5.ch5;

import org.springframework.aop.Advisor;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.util.StopWatch;

public class Main {

    public static void main(String[] args) {

        SimpleBean bean = new DefaultSimpleBean();

        Advisor advisor = new DefaultPointcutAdvisor(
                new TestPointcut(), new NoOpBeforeAdvice());

        runCglibTests(advisor, bean);
        runCglibFrozenTests(advisor, bean);
        runJdkTests(advisor, bean);
    }

    private static void runCglibTests(Advisor advisor, SimpleBean bean) {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setProxyTargetClass(true);
        proxyFactory.setTarget(bean);
        proxyFactory.addAdvisor(advisor);

        SimpleBean proxy = (SimpleBean) proxyFactory.getProxy();
        System.out.println("Running CGLIB (Standard) Tests");
        test(proxy);
    }

    private static void runCglibFrozenTests(Advisor advisor, SimpleBean bean) {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setProxyTargetClass(true);
        proxyFactory.setTarget(bean);
        proxyFactory.addAdvisor(advisor);
        proxyFactory.setFrozen(true);

        SimpleBean proxy = (SimpleBean) proxyFactory.getProxy();
        System.out.println("Running CGLIB (Frozen) Tests");
        test(proxy);
    }

    private static void runJdkTests(Advisor advisor, SimpleBean bean) {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(bean);
        proxyFactory.addAdvisor(advisor);
        proxyFactory.setInterfaces(SimpleBean.class);

        SimpleBean proxy = (SimpleBean) proxyFactory.getProxy();
        System.out.println("Running JDK Tests");
        test(proxy);
    }

    private static void test(SimpleBean bean) {

        final int TIMES = 500000;

        StopWatch sw = null;

        System.out.println("Testing Advised Method");
        sw = new StopWatch();
        sw.start();
        for (int i = 0; i < TIMES; i++) {
            bean.advised();
        }
        sw.stop();
        System.out.println("Took " + sw.getTotalTimeMillis() + " ms");

        System.out.println("Testing Unadvised Method");
        sw = new StopWatch();
        sw.start();
        for (int i = 0; i < TIMES; i++) {
            bean.unadvised();
        }
        sw.stop();
        System.out.println("Took " + sw.getTotalTimeMillis() + " ms");

        System.out.println("Testing equals Method");
        sw.start();
        for (int i = 0; i < TIMES; i++) {
            bean.equals(bean);
        }
        sw.stop();
        System.out.println("Took " + sw.getTotalTimeMillis() + " ms");

        System.out.println("Testing hashCode Method");
        sw = new StopWatch();
        sw.start();
        for (int i = 0; i < TIMES; i++) {
            bean.hashCode();
        }
        sw.stop();
        System.out.println("Took " + sw.getTotalTimeMillis() + " ms");

        System.out.println("Testing Advised.getTargetClass Method");
        Advised advised = (Advised) bean;
        sw = new StopWatch();
        sw.start();
        for (int i = 0; i < TIMES; i++) {
            advised.getTargetClass();
        }
        sw.stop();
        System.out.println("Took " + sw.getTotalTimeMillis() + " ms");

        System.out.println(">>>");
    }
}
