package jun.prospring5.ch3;

import org.springframework.context.support.GenericXmlApplicationContext;

public class Main {

    public static void main(String[] args) {

        GenericXmlApplicationContext appContext =
                new GenericXmlApplicationContext();

        appContext.load("app-context-xml.xml");
        appContext.refresh();

        Singer parent = (Singer) appContext.getBean("parent");
        System.out.println("Parent:\n" + parent);
        Singer child = (Singer) appContext.getBean("child");
        System.out.println("Child:\n" + child);
    }
}
