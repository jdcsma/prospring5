package jun.prospring5.ch12;

import jun.prospring5.ch12.configuration.AppConfiguration;
import jun.prospring5.ch12.producer.MessageSender;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        GenericApplicationContext context =
                new AnnotationConfigApplicationContext(
                        AppConfiguration.class);

        MessageSender sender = context.getBean(
                "messageSender", MessageSender.class);

        for (int i = 0; i < 10; ++i) {
            sender.sendMessage("Test message: " + i);
        }

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

        context.close();
    }
}
