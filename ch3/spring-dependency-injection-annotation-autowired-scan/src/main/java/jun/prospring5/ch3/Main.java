package jun.prospring5.ch3;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Main {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(
                        AutowiredConfiguration.class);

        Target t = applicationContext.getBean(Target.class);

        applicationContext.close();

    }
}
