package jun.prospring5.ch4;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.util.Assert;

import java.io.IOException;
import java.util.Arrays;

@SpringBootApplication
public class Main {

    private static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        ConfigurableApplicationContext appContext =
                SpringApplication.run(Main.class, args);
        Assert.notNull(appContext, "appContext is null");
        logger.info("The beans you were looking for:");

        // listing all bean definition names
        Arrays.stream(appContext.getBeanDefinitionNames()).forEach(logger::info);

        HelloSpringBoot bean =
                (HelloSpringBoot) appContext.getBean(
                        "helloSpringBoot");
        bean.sayHi();

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

        appContext.close();
    }
}
