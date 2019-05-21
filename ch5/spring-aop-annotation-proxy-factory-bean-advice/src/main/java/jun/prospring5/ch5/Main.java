package jun.prospring5.ch5;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext
                appContext = new AnnotationConfigApplicationContext(
                        ProxyFactoryBeanConfiguration.class);

        System.out.println(">> documentaristOne:");

        Documentarist documentaristOne =
                appContext.getBean("documentaristOne",
                        Documentarist.class);

        documentaristOne.execute();

        System.out.println(">> documentaristTwo:");

        Documentarist documentaristTwo =
                appContext.getBean("documentaristTwo",
                        Documentarist.class);

        documentaristTwo.execute();
    }
}
