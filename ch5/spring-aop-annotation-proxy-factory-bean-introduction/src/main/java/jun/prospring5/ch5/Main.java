package jun.prospring5.ch5;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext appContext =
                new AnnotationConfigApplicationContext(
                        ProxyFactoryBeanConfiguration.class);

        Contact bean = (Contact) appContext.getBean("bean");
        IsModified isModified = (IsModified) bean;

        System.out.println("Is Contact: " + (bean instanceof Contact));
        System.out.println("Is IsModified: " + (bean instanceof IsModified));

        System.out.println("Has been modified: " + isModified.isModified());
        bean.setName("John Mayer");

        System.out.println("Has been modified: " + isModified.isModified());
        bean.setName("Eric Clapton");

        System.out.println("Has been modified: " + isModified.isModified());
    }
}
