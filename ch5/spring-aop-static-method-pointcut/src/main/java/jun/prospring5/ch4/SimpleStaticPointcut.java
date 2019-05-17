package jun.prospring5.ch4;

import org.apache.commons.lang3.StringUtils;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.StaticMethodMatcherPointcut;

import java.lang.reflect.Method;

public class SimpleStaticPointcut extends StaticMethodMatcherPointcut {

    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        return StringUtils.equals("sing", method.getName());
    }

    @Override
    public ClassFilter getClassFilter() {
        return clazz -> clazz == GoodGuitarist.class;
    }
}
