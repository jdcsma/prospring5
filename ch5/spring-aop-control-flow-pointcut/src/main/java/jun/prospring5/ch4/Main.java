package jun.prospring5.ch4;

import org.springframework.aop.Advisor;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.ControlFlowPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;

public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }

    public void run() {
        SimpleBean bean = new SimpleBean();

        Pointcut pointcut = new ControlFlowPointcut(
                Main.class, "test");

        Advisor advisor = new DefaultPointcutAdvisor(
                pointcut, new SimpleBeanAdvice());

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(bean);
        proxyFactory.addAdvisor(advisor);

        SimpleBean proxy = (SimpleBean) proxyFactory.getProxy();
        System.out.println(">>> Trying normal invoke");
        proxy.foo();
        System.out.println(">>> Trying test invoke");
        test(proxy);
    }

    public void test(SimpleBean bean) {
        bean.foo();
    }
}
