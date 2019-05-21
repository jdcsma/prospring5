package jun.prospring5.ch5;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext appContext =
                new AnnotationConfigApplicationContext(
                        AnnotationAspectjConfiguration.class);

        Documentarist documentarist = appContext.getBean(
                "documentarist", Documentarist.class);

        System.out.println("Documentarist >>");
        documentarist.execute();
    }
}
