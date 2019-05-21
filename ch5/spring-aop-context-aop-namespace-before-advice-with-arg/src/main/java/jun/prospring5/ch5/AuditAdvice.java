package jun.prospring5.ch5;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;

public class AuditAdvice {

    public void beforeAdviceWithArgument(
            JoinPoint joinPoint, Guitar guitar) {

        if (StringUtils.equals(guitar.name(), "Taylor")) {
            System.out.println("Executing: " + guitar.name() + " " +
                    joinPoint.getSignature().getDeclaringTypeName() +
                    " " + joinPoint.getSignature().getName());
        }
    }
}
