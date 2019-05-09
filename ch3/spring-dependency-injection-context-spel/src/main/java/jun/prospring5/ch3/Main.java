package jun.prospring5.ch3;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.util.Assert;

public class Main {

    public static void main(String[] args) {

        GenericXmlApplicationContext appContext =
                new GenericXmlApplicationContext();

        appContext.load("app-context-xml.xml");
        appContext.refresh();

        InjectSimple simple;

        simple = (InjectSimple) appContext.getBean("injectSimple");
        Assert.notNull(simple, "bean not found by name <injectSimple>");
        System.out.println("simple:" + simple.toString());

        simple = appContext.getBean(InjectSimple.class);
        Assert.notNull(simple, "bean not found by type <injectSimple.class>");
        System.out.println("simple:" + simple.toString());
    }
}
