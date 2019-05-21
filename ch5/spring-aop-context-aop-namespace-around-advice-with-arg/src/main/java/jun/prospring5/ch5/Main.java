package jun.prospring5.ch5;

import org.springframework.context.support.GenericXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
        GenericXmlApplicationContext appContext =
                new GenericXmlApplicationContext();
        appContext.load("classpath:app-context-xml.xml");
        appContext.refresh();

        Documentarist documentarist = appContext.getBean(
                "documentarist", Documentarist.class);

        System.out.println("Documentarist >>");
        documentarist.execute();
    }
}
