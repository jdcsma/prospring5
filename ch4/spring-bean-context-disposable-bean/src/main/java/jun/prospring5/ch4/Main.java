package jun.prospring5.ch4;

import org.springframework.context.support.GenericXmlApplicationContext;

public class Main {

    public static void main(String[] args) {

        GenericXmlApplicationContext appContext
                = new GenericXmlApplicationContext();
        appContext.load("app-context-xml.xml");
        appContext.refresh();

        DestructiveBeanWithInterface destructiveBean =
                (DestructiveBeanWithInterface) appContext.getBean(
                        "destructiveBean");

        System.out.println("Calling close:");
        // Deprecated - Use close() method instead of.
        // appContext.destroy();
        appContext.close();
        System.out.println("Called close:");
    }
}
