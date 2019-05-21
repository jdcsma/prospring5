package jun.prospring5.ch5;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

public class AgentDecorator implements AfterReturningAdvice {

    @Override
    public void afterReturning(
            Object returnValue, Method method,
            Object[] args, Object target)
            throws Throwable {
        System.out.println();
        System.out.println("After '" + method.getName() + "' have a look see." );
    }
}
