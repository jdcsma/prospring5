package jun.prospring5.ch4;

import jun.prospring5.ch4.other.HelloSpring;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan // equivalence to @ComponentScan(basePackages = {"jun.prospring5.ch4"})
public class Main {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext appContext =
                new AnnotationConfigApplicationContext(Main.class);

        HelloWorld hw = appContext.getBean(HelloWorld.class);
        hw.sayHi();

        HelloSpring hs = appContext.getBean(HelloSpring.class);
        hs.sayHi();

        appContext.close();
    }
}
