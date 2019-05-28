package jun.prospring5.ch7;

import jun.prospring5.ch7.dao.SingerDao;
import jun.prospring5.ch7.entity.Album;
import jun.prospring5.ch7.entity.Instrument;
import jun.prospring5.ch7.entity.Singer;
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
    private SingerDao singerDao;

    @Before
    public void jdbcSetup() {

        GenericXmlApplicationContext context =
                new GenericXmlApplicationContext();
        context.load("classpath:app-context-xml.xml");
        context.refresh();

        appContext = context;

        singerDao = appContext.getBean("singerDao", SingerDao.class);
    }

    @Test
    public void jdbcTest() {

        showSingers(singerDao.findAll());
        showSingersWithAlbum(singerDao.findAllWithAlbum());

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

        Instrument instrument = new Instrument();
        instrument.setInstrumentId("Guitar");

        singer.addAlbum(album);
        singer.addInstrument(instrument);

        singerDao.save(singer);

        showSingersWithAlbum(singerDao.findAllWithAlbum());

        Singer newSinger = singerDao.findById(singer.getId());
        Assert.assertTrue(StringUtils.equals(newSinger.getFirstName(), singer.getFirstName()));
        Assert.assertTrue(StringUtils.equals(newSinger.getLastName(), singer.getLastName()));

        singerDao.delete(singer);

        showSingersWithAlbum(singerDao.findAllWithAlbum());
    }

    @After
    public void jdbcDown() {
        appContext.close();
    }

    private void showSingers(List<Singer> singers) {
        singers.forEach(s -> {
            logger.info(s.toString());
        });
    }

    private void showSingersWithAlbum(List<Singer> singers) {
        singers.forEach(s -> {
            logger.info(s.toString());

            s.getAlbums().forEach(a -> logger.info("    " + a.toString()));
            s.getInstruments().forEach(i -> logger.info("    " + i.toString()));
        });
    }
}
