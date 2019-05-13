package jun.prospring5.ch4;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext appContext
                = new AnnotationConfigApplicationContext(
                        DestructiveBeanConfiguration.class);

        DestructiveBean bean =
                appContext.getBean(DestructiveBean.class);
        appContext.registerShutdownHook();
    }
}
