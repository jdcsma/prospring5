package jun.prospring5.ch8;

import jun.prospring5.ch8.configuration.JpaConfiguration;
import jun.prospring5.ch8.entity.Singer;
import jun.prospring5.ch8.service.SingerService;
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
                        JpaConfiguration.class);

        appContext = context;

        singerService = appContext.getBean(
                "singerService", SingerService.class);
    }

    @Test
    public void jdbcTest() {

        logger.info("--- showSingers: singerService.findAll()");
        showSingers(singerService.findAll());

        logger.info("--- showSingers: add singer");

        Singer singer = new Singer();
        singer.setFirstName("Ed");
        singer.setLastName("Sheeran");
        singer.setBirthDate(new Date((
                new GregorianCalendar(
                        1991, 2, 1991)
                        .getTime().getTime())));

        singer = singerService.save(singer);
        showSingers(singerService.findAll());

        logger.info("--- showSingers: update singer");

        sleep();

        singer = singerService.findById(singer.getId());
        singer.setFirstName("John");
        singer = singerService.save(singer);
        showSingers(singerService.findAll());

        Singer singerRevision;
        singerRevision = singerService.findAuditByRevision(singer.getId(), 1);
        logger.info("--- old singer with id " + singer.getId() + " and rev 1:");
        logger.info(singerRevision.toString());
        singerRevision = singerService.findAuditByRevision(singer.getId(), 2);
        logger.info("--- old singer with id " + singer.getId() + " and rev 2:");
        logger.info(singerRevision.toString());

        Singer newSinger = singerService.findById(singer.getId());
        Assert.assertTrue(StringUtils.equals(newSinger.getFirstName(), singer.getFirstName()));
        Assert.assertTrue(StringUtils.equals(newSinger.getLastName(), singer.getLastName()));

        sleep();

        logger.info("--- showSingers: delete singer");

        singerService.delete(singer);
        showSingers(singerService.findAll());
    }

    @After
    public void jdbcDown() {
        if (appContext != null) {
            appContext.close();
        }
    }

    private void sleep() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void showSingers(List<Singer> singers) {
        singers.forEach(s -> logger.info(s.toString()));
    }
}
