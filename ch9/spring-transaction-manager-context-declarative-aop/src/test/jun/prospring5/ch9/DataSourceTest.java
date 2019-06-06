package jun.prospring5.ch9;

import jun.prospring5.ch9.entity.Singer;
import jun.prospring5.ch9.service.SingerService;
import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

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

        GenericXmlApplicationContext context =
                new GenericXmlApplicationContext();
        context.load("classpath:app-context-xml.xml");
        context.refresh();

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

        logger.info("--- showSingers: singerService.countAllSingers()");
        logger.info("singer count: " + singerService.countAllSingers());

        Singer existsSinger = singerService.findById(singer.getId());
        Assert.assertTrue(StringUtils.equals(existsSinger.getFirstName(), singer.getFirstName()));
        Assert.assertTrue(StringUtils.equals(existsSinger.getLastName(), singer.getLastName()));

        logger.info("--- showSingers: singerService.delete()");
        singerService.delete(singer);
        showSingers(singerService.findAll());
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
