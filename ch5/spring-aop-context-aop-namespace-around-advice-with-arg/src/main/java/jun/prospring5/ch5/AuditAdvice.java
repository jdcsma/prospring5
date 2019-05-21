package jun.prospring5.ch5;

import org.aspectj.lang.ProceedingJoinPoint;

public class AuditAdvice {

    public Object aroundAdvice(
            ProceedingJoinPoint joinPoint, Guitar guitar)
            throws Throwable {
        System.out.println("Before execution: " +
                joinPoint.getSignature().getDeclaringTypeName() +
                " " + joinPoint.getSignature().getName() +
                " argument: " + guitar.name());

        Object retValue = joinPoint.proceed();

        System.out.println("After execution: " + joinPoint.getSignature().getDeclaringTypeName() +
                " " + joinPoint.getSignature().getName() + " argument: " + guitar.name());

        return retValue;
    }
}
