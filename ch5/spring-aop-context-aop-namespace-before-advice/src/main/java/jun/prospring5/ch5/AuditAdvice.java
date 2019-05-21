package jun.prospring5.ch5;

import org.aspectj.lang.JoinPoint;

public class AuditAdvice {

    public void beforeAdvice(JoinPoint joinPoint) {
        System.out.println("Executing: " +
                joinPoint.getSignature().getDeclaringTypeName() +
                " " + joinPoint.getSignature().getName());
    }
}
