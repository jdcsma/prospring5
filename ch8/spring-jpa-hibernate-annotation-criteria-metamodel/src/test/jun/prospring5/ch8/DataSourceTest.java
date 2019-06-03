package jun.prospring5.ch8;

import jun.prospring5.ch8.configuration.JpaConfiguration;
import jun.prospring5.ch8.entity.Album;
import jun.prospring5.ch8.entity.Instrument;
import jun.prospring5.ch8.service.InstrumentService;
import jun.prospring5.ch8.service.SingerService;
import jun.prospring5.ch8.entity.Singer;
import jun.prospring5.ch8.service.SingerSummaryService;
import jun.prospring5.ch8.view.SingerSummary;
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
    private SingerSummaryService singerSummaryService;
    private InstrumentService instrumentService;

    @Before
    public void jdbcSetup() {

//        AnnotationConfigApplicationContext context =
//                new AnnotationConfigApplicationContext(
//                        JpaConfiguration.class);

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext();
        context.register(JpaConfiguration.class, DatabaseInitializer.class);
        context.refresh();

        appContext = context;

        singerService = appContext.getBean(
                "singerService", SingerService.class);
        singerSummaryService = appContext.getBean(
                "singerSummaryService", SingerSummaryService.class);
        instrumentService = appContext.getBean(
                "instrumentService", InstrumentService.class);
    }

    @Test
    public void jdbcTest() {

        logger.info("--- singer summaries: singerSummaryService.displayAllSingerSummary()");
        singerSummaryService.displayAllSingerSummary();

        logger.info("--- singer summaries: singerSummaryService.displayAllSingerSummaryCriteriaQuery()");
        singerSummaryService.displayAllSingerSummaryCriteriaQuery();

        logger.info("--- singer summaries: singerSummaryService.findAllSingerSummery()");
        List<SingerSummary> singerSummaries =
                singerSummaryService.findAllSingerSummery();
        singerSummaries.forEach(s -> logger.info(s.toString()));

        logger.info("--- singer summaries: singerSummaryService.findAllSingerSummeryCriteriaQuery()");
        singerSummaries = singerSummaryService.findAllSingerSummeryCriteriaQuery();
        singerSummaries.forEach(s -> logger.info(s.toString()));

        logger.info("--- instruments: instrumentService.findAllByCriteriaQuery()");
        List<Instrument> instruments =
                instrumentService.findAllByCriteriaQuery();
        instruments.forEach(i -> logger.info(i.toString()));

        logger.info("--- instruments: instrumentService.findByCriteriaQuery()");
        instruments.forEach(i -> {
            instrumentService.findByCriteriaQuery(
                    i.getInstrumentId()).forEach(
                            ii -> logger.info(ii.toString()));
        });

        logger.info("--- showSingers: singerService.findAll()");
        showSingers(singerService.findAll());
        logger.info("--- showSingers: singerService.findAllWithDetails()");
        showSingersWithAlbum(singerService.findAllWithDetails());
        logger.info("--- showSingers: singerService.findAllByNativeQuery()");
        showSingers(singerService.findAllByNativeQuery());
        logger.info("--- showSingers: singerService.findByCriteriaQuery(\"John\", \"Mayer\")");
        showSingersWithAlbum(singerService.findByCriteriaQuery("John", "Mayer"));

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

        singerService.save(singer);

        showSingersWithAlbum(singerService.findAllWithDetails());

        Singer newSinger = singerService.findById(singer.getId());
        Assert.assertTrue(StringUtils.equals(newSinger.getFirstName(), singer.getFirstName()));
        Assert.assertTrue(StringUtils.equals(newSinger.getLastName(), singer.getLastName()));

        singerService.delete(singer);

        showSingersWithAlbum(singerService.findAllWithDetails());
    }

    @After
    public void jdbcDown() {
        appContext.close();
    }

    private void showSingers(List<Singer> singers) {
        singers.forEach(s -> logger.info(s.toString()));
    }

    private void showSingersWithAlbum(List<Singer> singers) {
        singers.forEach(s -> {
            logger.info(s.toString());
            s.getAlbums().forEach(a -> logger.info("    " + a.toString()));
            s.getInstruments().forEach(i -> logger.info("    " + i.toString()));
        });
    }
}
