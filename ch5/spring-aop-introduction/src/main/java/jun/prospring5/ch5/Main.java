package jun.prospring5.ch5;

import org.springframework.aop.IntroductionAdvisor;
import org.springframework.aop.framework.ProxyFactory;

public class Main {

    public static void main(String[] args) {

        Contact contact = new Contact();
        contact.setName("John Mayer");

        IntroductionAdvisor advisor = new IsModifiedAdvisor();

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(contact);
        proxyFactory.addAdvisor(advisor);
        proxyFactory.setOptimize(true);

        Contact proxy = (Contact) proxyFactory.getProxy();
        IsModified proxyInterface = (IsModified) proxy;

        System.out.println("Is Contract: " + (proxy instanceof Contact));
        System.out.println("Is IsModified: " + (proxy instanceof IsModified));

        System.out.println("Has been modified: " +
                proxyInterface.isModified());

        proxy.setName("John Mayer");

        System.out.println("Has been modified: " +
                proxyInterface.isModified());

        proxy.setName("Eric Clapton");

        System.out.println("Has been modified: " +
                proxyInterface.isModified());
    }
}
