package jun.prospring5.ch4;

import org.apache.commons.lang3.StringUtils;
import org.springframework.aop.Advisor;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.ComposablePointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.StaticMethodMatcher;

import java.lang.reflect.Method;

public class Main {

    public static void main(String[] args) {

        GrammyGuitarist johnMayer = new GrammyGuitarist();

        ComposablePointcut pointcut =
                new ComposablePointcut(ClassFilter.TRUE,
                        new SingMethodMatcher());

        System.out.println("Test 1 >> ");
        GrammyGuitarist proxy = getProxy(pointcut, johnMayer);
        invokeTest(proxy);
        System.out.println();

        System.out.println("Test 2 >> ");
        pointcut.union(new TalkMethodMatcher());
        proxy = getProxy(pointcut, johnMayer);
        invokeTest(proxy);
        System.out.println();

        System.out.println("Test 3 >> ");
        pointcut.intersection(new RestMethodMatcher());
        proxy = getProxy(pointcut, johnMayer);
        invokeTest(proxy);
        System.out.println();
    }

    private static GrammyGuitarist getProxy(Pointcut pointcut, Singer target) {

        Advisor advisor = new DefaultPointcutAdvisor(
                pointcut, new SimpleAdvice());

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(target);
        proxyFactory.addAdvisor(advisor);

        return (GrammyGuitarist) proxyFactory.getProxy();
    }

    private static void invokeTest(Singer singer) {
        singer.sing();
        singer.sing(new Guitar());
        singer.talk();
        singer.rest();
    }

    private static class SingMethodMatcher extends StaticMethodMatcher {
        @Override
        public boolean matches(Method method, Class<?> targetClass) {
            return StringUtils.startsWith(method.getName(), "si");
        }
    }

    private static class TalkMethodMatcher extends StaticMethodMatcher {
        @Override
        public boolean matches(Method method, Class<?> targetClass) {
            return StringUtils.equals("talk", method.getName());
        }
    }

    private static class RestMethodMatcher extends StaticMethodMatcher {
        @Override
        public boolean matches(Method method, Class<?> targetClass) {
            return StringUtils.endsWith(method.getName(), "st");
        }
    }
}
