package jun.prospring5.ch4;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext appContext =
                new ClassPathXmlApplicationContext(
                        "app-context-xml.xml");

        MessageRenderer renderer =
                (MessageRenderer) appContext.getBean(
                        "renderer");

        renderer.render();

        appContext.close();
    }
}
