package jun.prospring5.ch12.configuration;

import jun.prospring5.ch12.common.service.SingerService;
import jun.prospring5.ch12.common.entity.Singer;
import jun.prospring5.ch12.common.entity.SingerBuilder;
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
public class DBInitializer {

    private static Logger logger =
            LoggerFactory.getLogger(DBInitializer.class);

    @Autowired
    private SingerService singerService;

    @PostConstruct
    public void initDB() {

        logger.info("Starting database initialization ...");

        List<Singer> singers = new ArrayList<>();

        Singer singer = new SingerBuilder()
                .setFirstName("John")
                .setLastName("Mayer")
                .setBirthDate(new GregorianCalendar(1977, 9, 16)
                        .getTime())
                .build();
        singers.add(singer);

        singer = new SingerBuilder()
                .setFirstName("Eric")
                .setLastName("Clapton")
                .setBirthDate(new Date(
                        new GregorianCalendar(1945, 3, 30)
                                .getTime().getTime()))
                .build();

        singers.add(singer);

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
