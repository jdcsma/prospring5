package jun.prospring5.ch5;

import org.springframework.context.support.GenericXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
        GenericXmlApplicationContext appContext =
                new GenericXmlApplicationContext();
        appContext.load("classpath:app-context-xml.xml");
        appContext.refresh();

        Documentarist documentaristOne = appContext.getBean(
                "documentaristOne", Documentarist.class);
        Documentarist documentaristTwo = appContext.getBean(
                "documentaristTwo", Documentarist.class);

        System.out.println("Documentarist One >>");
        documentaristOne.execute();

        System.out.println("Documentarist Two >>");
        documentaristTwo.execute();
    }
}
