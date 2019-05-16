package jun.prospring5.ch4;

import org.springframework.aop.framework.ProxyFactory;
import org.springframework.cglib.proxy.Proxy;

public class Main {

    public static void main(String[] args) {

        ErrorBean bean = new ErrorBean();
        ErrorHandler handler = new ErrorHandler();

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(bean);
        proxyFactory.addAdvice(handler);

        ErrorBean proxy = (ErrorBean) proxyFactory.getProxy();

        try {
            proxy.errorMethod1();
        } catch (Exception e) {
            System.out.println("catch generic exception.");
        }

        System.out.println();

        try {
            proxy.errorMethod2();
        } catch (IllegalArgumentException e) {
            System.out.println("catch illegal argument exception.");
        }
    }
}
