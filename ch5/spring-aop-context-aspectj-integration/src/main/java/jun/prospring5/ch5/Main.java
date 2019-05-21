package jun.prospring5.ch5;

import org.springframework.context.support.GenericXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
        GenericXmlApplicationContext appContext =
                new GenericXmlApplicationContext();
        appContext.load("classpath:app-context-xml.xml");
        appContext.refresh();

        MessageWriter writer = new MessageWriter();
        writer.writeMessage();
        System.out.println(">>>");
        writer.foo();
    }
}
