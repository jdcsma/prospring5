package jun.prospring5.ch5;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class AgentDecorator implements MethodBeforeAdvice {

    @Override
    public void before(
            Method method, Object[] args, Object target)
            throws Throwable {
        System.out.println("Before '" + method.getName() + "' have a look see." );
    }
}
