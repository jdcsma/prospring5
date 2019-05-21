package jun.prospring5.ch5;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class IsModifiedMixin extends
        DelegatingIntroductionInterceptor
        implements IsModified {

    private static final Class[] EMPTY_PARAM_TYPES;
    private static final Object[] EMPTY_PARAM_OBJECTS;

    private boolean isModified = false;
    private Map<Method, Method> methodCache = new HashMap<>();

    static {
        EMPTY_PARAM_TYPES = new Class[]{};
        EMPTY_PARAM_OBJECTS = new Object[]{};
    }

    @Override
    public boolean isModified() {
        return isModified;
    }

    @Override
    public Object invoke(MethodInvocation mi) throws Throwable {

        if (!isModified) {
            if (mi.getMethod().getName().startsWith("set") &&
                    mi.getArguments().length == 1) {
                Method getter = getGatter(mi.getMethod());
                if (getter != null) {
                    Object newValue = mi.getArguments()[0];
                    Object oldValue = getter.invoke(
                            mi.getThis(), EMPTY_PARAM_OBJECTS);
                    if (newValue == null && oldValue == null) {
                        isModified = false;
                    } else if (newValue == null && oldValue != null) {
                        isModified = true;
                    } else if (newValue != null && oldValue == null) {
                        isModified = true;
                    } else {
                        isModified = !newValue.equals(oldValue);
                    }
                }
            }
        }

        return super.invoke(mi);
    }

    private Method getGatter(Method setter) {

        Method getter = methodCache.get(setter);

        if (getter != null) {
            return getter;
        }

        String getterName = setter.getName()
                .replaceFirst("set", "get");

        try {
            getter = setter.getDeclaringClass().getMethod(
                    getterName, EMPTY_PARAM_TYPES);
            synchronized (methodCache) {
                methodCache.put(setter, getter);
            }
        } catch (NoSuchMethodException e) {
            return null;
        }

        return getter;
    }
}
