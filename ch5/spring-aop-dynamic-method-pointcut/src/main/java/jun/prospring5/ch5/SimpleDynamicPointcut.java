package jun.prospring5.ch5;

import org.apache.commons.lang3.StringUtils;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.DynamicMethodMatcherPointcut;

import java.lang.reflect.Method;

public class SimpleDynamicPointcut extends DynamicMethodMatcherPointcut {

    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        System.out.println("Static check for " + method.getName());
        return StringUtils.equals("sing", method.getName());
    }

    @Override
    public boolean matches(Method method, Class<?> targetClass, Object... args) {
        System.out.print("Dynamic check for " + method.getName() + ": ");
        boolean matched = StringUtils.equals((String) args[0], "Something in the Way");
        System.out.println(matched ? " match" : "mismatch");
        return matched;
    }

    @Override
    public ClassFilter getClassFilter() {
        return clazz -> clazz == GoodSinger.class;
    }
}
