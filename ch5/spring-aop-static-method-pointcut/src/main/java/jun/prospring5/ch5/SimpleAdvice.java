package jun.prospring5.ch5;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class SimpleAdvice implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation)
            throws Throwable {
        System.out.println(">> Invoking " + invocation.getMethod().getName());
        Object retValue = invocation.proceed();
        System.out.println(">> Done\n");
        return retValue;
    }
}
