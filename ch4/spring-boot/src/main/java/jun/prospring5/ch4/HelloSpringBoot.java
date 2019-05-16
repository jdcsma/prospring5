package jun.prospring5.ch4;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class HelloSpringBoot {

    private static Logger logger =
            LoggerFactory.getLogger(HelloSpringBoot.class);

    public void sayHi() {
        logger.info("Hello Spring Boot!");
    }
}
