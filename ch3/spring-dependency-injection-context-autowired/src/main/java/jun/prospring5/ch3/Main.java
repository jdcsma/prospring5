package jun.prospring5.ch3;

import org.springframework.context.support.GenericXmlApplicationContext;

public class Main {

    public static void main(String[] args) {

        GenericXmlApplicationContext appContext
                = new GenericXmlApplicationContext();
        appContext.load("app-context-xml.xml");
        appContext.refresh();

        Target target1 = (Target) appContext.getBean("targetByName");
        Target target2 = (Target) appContext.getBean("targetByType");
        Target target3 = (Target) appContext.getBean("targetConstructor");
    }
}
