package jun.prospring5.ch12;

import jun.prospring5.ch12.common.entity.Singer;
import jun.prospring5.ch12.common.service.SingerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Main {

    private static Logger logger =
            LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(
                        RmiConfiguration.class);

        SingerService singerService = context.getBean(
                "singerService", SingerService.class);

        List<Singer> singers = singerService.findAll();
        logger.info("-------- show singers: singerService.findAll()");
        singers.forEach(s -> logger.info("singer:" + s.toString()));

        context.close();
    }
}
