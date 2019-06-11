package jun.prospring5.ch9;

import jun.prospring5.ch9.configuration.JtaConfiguration;
import jun.prospring5.ch9.configuration.ServiceConfiguration;
import jun.prospring5.ch9.entity.Singer;
import jun.prospring5.ch9.service.SingerService;
import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class DataSourceTest {

    private static Logger logger =
            LoggerFactory.getLogger(DataSourceTest.class);

    private GenericApplicationContext appContext;
    private SingerService singerService;

    @Before
    public void jdbcSetup() {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(
                        ServiceConfiguration.class,
                        JtaConfiguration.class);

        appContext = context;

        singerService = appContext.getBean(
                "singerService", SingerService.class);
    }

    @Test
    public void jdbcTest() {

        logger.info("--- showSingers: singerService.findAll()");
        showSingers(singerService.findAll());

        Singer singer = new Singer();
        singer.setFirstName("Ed");
        singer.setLastName("Sheeran");
        singer.setBirthDate(new Date((
                new GregorianCalendar(
                        1991, 2, 1991)
                        .getTime().getTime())));

        logger.info("--- showSingers: singerService.save()");
        singerService.save(singer);
        showSingers(singerService.findAll());

        logger.info("--- showSingers: singerService.findById()");
        List<Singer> existsSingers = singerService.findById(singer.getId());
        showSingers(existsSingers);

        logger.info("--- showSingers: singerService.delete()");
        singerService.delete(singer);
        showSingers(singerService.findAll());

        singerService.deleteAll();
    }

    @After
    public void jdbcDown() {
        if (appContext != null) {
            appContext.close();
        }
    }

    private void showSingers(List<Singer> singers) {
        singers.forEach(s -> logger.info(s.toString()));
    }
}
