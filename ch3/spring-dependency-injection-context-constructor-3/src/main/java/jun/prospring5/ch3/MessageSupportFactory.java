package jun.prospring5.ch3;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;;

public class MessageSupportFactory {

    public static MessageRenderer getMessageRenderer() {

        ApplicationContext appContext =
                new ClassPathXmlApplicationContext(
                        "app-context-xml.xml");

        MessageRenderer renderer = appContext.getBean(
                "messageRenderer", MessageRenderer.class);

        return renderer;
    }
}
