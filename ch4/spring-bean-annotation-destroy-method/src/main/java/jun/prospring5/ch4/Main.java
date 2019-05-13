package jun.prospring5.ch4;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext appContext
                = new AnnotationConfigApplicationContext(
                        DestructiveBeanConfiguration.class);

        DestructiveBean bean =
                appContext.getBean(DestructiveBean.class);

        System.out.println("Calling close:");
        // Deprecated - Use close() method instead of.
        // appContext.destroy();
        appContext.close();
        System.out.println("Called close:");
    }
}
