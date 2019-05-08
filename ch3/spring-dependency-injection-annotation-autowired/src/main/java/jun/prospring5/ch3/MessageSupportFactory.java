package jun.prospring5.ch3;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MessageSupportFactory {

    public static MessageRenderer getMessageRenderer() {

        ApplicationContext appContext =
                new AnnotationConfigApplicationContext(
                        MessageSupportConfiguration.class);

        MessageRenderer renderer =
                appContext.getBean(
                        "messageRenderer", MessageRenderer.class);

        return renderer;
    }
}
