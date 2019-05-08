package jun.prospring5.ch3;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MessageSupportFactory {

    public static MessageRenderer getMessageRenderer() {

        ApplicationContext appContext =
                new GenericXmlApplicationContext(
                        "app-context-annotation.xml");

        MessageRenderer renderer = appContext.getBean(
                "messageRenderer", MessageRenderer.class);

        return renderer;
    }
}
