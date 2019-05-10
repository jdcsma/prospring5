package jun.prospring5.ch3;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.util.Assert;

public class Main {

    public static void main(String[] args) {

        GenericXmlApplicationContext appContext =
                new GenericXmlApplicationContext();

        appContext.load("app-context-xml.xml");
        appContext.refresh();

        Target t = null;

        System.out.println("Using byName:\n");
        t = (Target) appContext.getBean("targetByName");
        System.out.println();

        System.out.println("Using byType:\n");
        t = (Target) appContext.getBean("targetByType");
        System.out.println();

        System.out.println("Using constructor:\n");
        t = (Target) appContext.getBean("targetConstructor");
        System.out.println();

        appContext.close();
    }
}
