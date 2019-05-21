package jun.prospring5.ch5;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class SimpleBeanAdvice implements MethodBeforeAdvice {
    @Override
    public void before(
            Method method, Object[] args,
            Object target) throws Throwable {
        System.out.println("Before method: " + method.getName());
    }
}
