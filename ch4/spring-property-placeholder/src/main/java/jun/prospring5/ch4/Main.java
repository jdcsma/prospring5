package jun.prospring5.ch4;

import org.springframework.context.support.GenericXmlApplicationContext;

public class Main {

    public static void main(String[] args) {

        GenericXmlApplicationContext appContext
                = new GenericXmlApplicationContext();

        appContext.load("classpath:app-context-xml.xml");
        appContext.refresh();

        AppProperty appProperty =
                (AppProperty) appContext.getBean(
                        "appProperty");

        System.out.println("application name :" + appProperty.getApplicationName());
        System.out.println("user home: " + appProperty.getUserHome());

        appContext.close();
    }
}
