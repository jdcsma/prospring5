package jun.prospring5.ch5;

import jun.prospring5.ch5.dao.SingerDao;
import jun.prospring5.ch5.dao.implementation.PlainSingerDao;
import jun.prospring5.ch5.entity.Singer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class Main {

    private static Logger logger =
            LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        SingerDao singerDao = new PlainSingerDao();

        listSingers(singerDao);

        logger.info("--------------------");
        logger.info("Insert a new singer");

        Singer singer = new Singer();
        singer.setFirstName("Ed");
        singer.setLastName("Sheeran");
        singer.setBirthDate(new Date((
                new GregorianCalendar(
                        1991, 2, 1991)
                        .getTime().getTime())));
        singerDao.insert(singer);

        logger.info("Listing singer data after new singer created:");
        listSingers(singerDao);

        logger.info("--------------------");
        logger.info("Deleting the previous created singer");

        singerDao.delete(singer.getId());

        logger.info("Listing singer data after new singer delete:");
        listSingers(singerDao);
    }

    private static void listSingers(SingerDao singerDao) {
        List<Singer> singers = singerDao.findAll();
        for (Singer singer : singers) {
            logger.info(singer.toString());
        }
    }
}
