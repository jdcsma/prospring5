package jun.prospring5.ch5;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AnnotationAspectjAdvice {

    @Pointcut("execution(* jun.prospring5.ch5..sing*(jun.prospring5.ch5.Guitar)) && args(guitar)")
    public void singExecution(Guitar guitar) {
    }

    @Pointcut("bean(john*)")
    public void isJohn() {
    }

    @Before("singExecution(guitar) && isJohn()")
    public void beforeAdvice(JoinPoint joinPoint, Guitar guitar) {
        if (StringUtils.equals(guitar.name(), "Taylor")) {
            System.out.println("beforeAdvice: Executing: " +
                    joinPoint.getSignature().getDeclaringTypeName() +
                    " " + joinPoint.getSignature().getName() +
                    " argument: " + guitar.name());
        }
    }

    @Around("singExecution(guitar) && isJohn()")
    public Object aroundAdvice(
            ProceedingJoinPoint joinPoint, Guitar guitar)
            throws Throwable {

        System.out.println("aroundAdvice: Before execution: " +
                joinPoint.getSignature().getDeclaringTypeName() +
                " " + joinPoint.getSignature().getName() +
                " argument: " + guitar.name());

        Object retValue = joinPoint.proceed();

        System.out.println("aroundAdvice: After execution: " +
                joinPoint.getSignature().getDeclaringTypeName() +
                " " + joinPoint.getSignature().getName() +
                " argument: " + guitar.name());

        return retValue;
    }
}
