package jun.prospring5.ch9;

import jun.prospring5.ch9.entity.Singer;
import jun.prospring5.ch9.entity.SingerBuilder;
import jun.prospring5.ch9.service.SingerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Service
public class DatabaseInitializer {

    private static Logger logger =
            LoggerFactory.getLogger(DatabaseInitializer.class);

    @Autowired
    private SingerService singerService;

    @PostConstruct
    public void initDB() {

        logger.info("Starting database initialization ...");

        List<Singer> singers = new ArrayList<>();

        singers.add(new SingerBuilder()
                .setFirstName("John")
                .setLastName("Mayer")
                .setBirthDate(new GregorianCalendar(1977, 9, 16)
                        .getTime())
                .build());

        singers.add(new SingerBuilder()
                .setFirstName("Eric")
                .setLastName("Clapton")
                .setBirthDate(new Date(
                        new GregorianCalendar(1945, 3, 30)
                                .getTime().getTime()))
                .build());

        singers.add(new SingerBuilder()
                .setFirstName("John")
                .setLastName("Butler")
                .setBirthDate(new Date(
                        new GregorianCalendar(1975, 4, 1)
                                .getTime().getTime()))
                .build());

        singers.forEach(s -> singerService.save(s));

        logger.info("Database initialization finished.");
    }
}
