package jun.prospring5.ch3;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.util.Assert;

public class Main {

    public static void main(String[] args) {

        ApplicationContext appContext =
                new GenericXmlApplicationContext(
                        "app-context-annotation.xml");

        InjectSimple simple;

        simple = (InjectSimple) appContext.getBean("injectSimple");
        Assert.notNull(simple, "bean not found by name <injectSimple>");
        System.out.println("simple:" + simple.toString());

        simple = appContext.getBean(InjectSimple.class);
        Assert.notNull(simple, "bean not found by type <injectSimple.class>");
        System.out.println("simple:" + simple.toString());
    }
}
