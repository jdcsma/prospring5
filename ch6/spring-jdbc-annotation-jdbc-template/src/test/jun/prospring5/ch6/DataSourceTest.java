package jun.prospring5.ch6;

import jun.prospring5.ch6.configuration.DataSourceConfiguration;
import jun.prospring5.ch6.dao.SingerDao;
import jun.prospring5.ch6.entity.Album;
import jun.prospring5.ch6.entity.Singer;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.Assert;

import java.sql.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class DataSourceTest {

    private static Logger logger =
            LoggerFactory.getLogger(DataSourceTest.class);

    @Test
    public void jdbcTemplateTest() {

        AnnotationConfigApplicationContext
                appContext = new AnnotationConfigApplicationContext(
                        DataSourceConfiguration.class);

        SingerDao singerDao = appContext.getBean(
                "jdbcSingerDao", SingerDao.class);

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
        Album album = new Album();
        album.setTitle("No. 5 Collaborations Project");
        album.setReleaseDate(new Date(new GregorianCalendar(
                2011, 1, 1991)
                .getTime().getTime()));
        singer.addAlbum(album);
        singerDao.insert(singer);

        logger.info("Listing singer data after new singer created:");
        listSingers(singerDao);

        logger.info("Listing singer albums after new singer updated:");
        List<Album> albums = singerDao.findAlbums(singer.getId());
        albums.forEach(a -> logger.info(a.toString()));

        logger.info("--------------------");
        logger.info("Updating the previous created singer");

        singer.setDeath(true);
        singerDao.update(singer);

        logger.info("Listing singer data after new singer updated:");
        listSingers(singerDao);

        logger.info("--------------------");
        logger.info("Deleting the previous created singer");

        singerDao.delete(singer.getId());

        logger.info("Listing singer data after new singer delete:");
        listSingers(singerDao);
    }

    private static void listSingers(SingerDao singerDao) {
        List<Singer> singers = singerDao.findAll();
        List<Singer> singersWithDetail = singerDao.findAllWithDetail();
        Assert.isTrue(singers.size() == singersWithDetail.size(),
                "singers.size() != singersWithDetail.size()");
        for (Singer singer : singersWithDetail) {

            Singer found = singerDao.findById(singer.getId());
            String firstName = singerDao.findFirstNameById(found.getId());
            String lastName = singerDao.findLastNameById(found.getId());
            Assert.isTrue(StringUtils.equals(singer.getFirstName(), firstName),
                    "singer.getFirstName() != firstName");
            Assert.isTrue(StringUtils.equals(singer.getLastName(), lastName),
                    "singer.getLastName(), lastName");

            logger.info(singer.toString());
            for (Album album : singer.getAlbums()) {
                logger.info("    " + album.toString());
            }
        }
    }
}
