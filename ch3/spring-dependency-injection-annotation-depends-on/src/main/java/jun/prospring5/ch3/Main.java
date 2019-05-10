package jun.prospring5.ch3;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Main {

    public static void main(String[] args) {

        ApplicationContext appContext =
                new GenericXmlApplicationContext(
                        "app-context-annotation.xml");

        Singer singer = (Singer) appContext.getBean("johnMayer");
        singer.sing();
    }
}
