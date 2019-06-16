package jun.prospring5.ch12.test;

import jun.prospring5.ch12.RmiConfiguration;
import jun.prospring5.ch12.common.entity.Singer;
import jun.prospring5.ch12.common.entity.SingerBuilder;
import jun.prospring5.ch12.common.service.SingerService;
import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@ContextConfiguration(classes = RmiConfiguration.class)
@RunWith(SpringRunner.class)
public class RmiTests {

    private static Logger logger =
            LoggerFactory.getLogger((RmiTests.class));

    @Autowired
    private SingerService singerService;

    @Test
    public void test() {

        List<Singer> singers = singerService.findAll();
        logger.info("-------- show singer: singerService.findAll");
        showSingers(singers);

        singers = singerService.findByFirstName("John");
        logger.info("-------- show singer: singerService.findByFirstName");
        showSingers(singers);

        Singer singer = new SingerBuilder()
                .setFirstName("Kurt")
                .setLastName("Cobain")
                .setBirthDate(DateTime.parse("1967-02-20").toDate())
                .build();

        logger.info("-------- save singer:");
        singer = singerService.save(singer);

        logger.info("-------- show singer: singerService.findById");
        singer = singerService.findById(singer.getId());
        logger.info("singer:" + singer.toString());

        logger.info("-------- show singer: singerService.findAll");
        showSingers(singerService.findAll());

        logger.info("-------- delete singer:");
        singerService.delete(singer);
        showSingers(singerService.findAll());
    }

    private static void showSingers(List<Singer> singers) {
        singers.forEach(s -> logger.info("singer:" + s.toString()));
    }
}
