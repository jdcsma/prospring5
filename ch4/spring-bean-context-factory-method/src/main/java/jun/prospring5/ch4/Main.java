package jun.prospring5.ch4;

import org.springframework.context.support.GenericXmlApplicationContext;

public class Main {

    public static void main(String[] args) {

        GenericXmlApplicationContext appContext
                = new GenericXmlApplicationContext();
        appContext.load("app-context-xml.xml");
        appContext.refresh();

        MessageDigester digester =
                (MessageDigester) appContext.getBean(
                        "digester");

        digester.digest("Hi, FactoryBean.");
    }
}
