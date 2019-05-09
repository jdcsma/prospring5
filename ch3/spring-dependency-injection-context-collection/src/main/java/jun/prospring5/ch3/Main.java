package jun.prospring5.ch3;

import org.springframework.context.support.GenericXmlApplicationContext;

public class Main {

    public static void main(String[] args) {

        GenericXmlApplicationContext
                appContext = new GenericXmlApplicationContext();

        appContext.load("app-context-xml.xml");
        appContext.refresh();

        InjectCollection ic = (InjectCollection)
                appContext.getBean("injectCollection");

        ic.displayInfo();
    }
}
