package jun.prospring5.ch4;

import org.springframework.aop.ThrowsAdvice;

import java.lang.reflect.Method;

public class ErrorHandler implements ThrowsAdvice {

    public void afterThrowing(Exception ex) throws Throwable {
        System.out.println("***");
        System.out.println("Generic Exception Capture");
        System.out.println("Caught: " + ex.getClass().getName());
        System.out.println("***");
    }

    public void afterThrowing(
            Method method, Object args, Object target,
            IllegalArgumentException ex) throws Throwable {
        System.out.println("***");
        System.out.println("IllegalArgumentException Capture");
        System.out.println("Caught: " + ex.getClass().getName());
        System.out.println("Method: " + method.getName());
        System.out.println("***");
    }
}
