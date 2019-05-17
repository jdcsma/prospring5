package jun.prospring5.ch4;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class SimpleAdvice implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation)
            throws Throwable {
        System.out.println("-- advice: before " + invocation.getMethod().getName());
        Object retValue = invocation.proceed();
        System.out.println("-- advice: after " + invocation.getMethod().getName());
        return retValue;
    }
}
