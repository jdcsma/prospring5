package jun.prospring5.ch7.configuration;

import jun.prospring5.ch7.dao.InstrumentDao;
import jun.prospring5.ch7.dao.SingerDao;
import jun.prospring5.ch7.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.function.Consumer;

@Service
public class DatabaseInitializer {

    private static Logger logger =
            LoggerFactory.getLogger(DatabaseInitializer.class);

    @Autowired
    private SingerDao singerDao;

    @Autowired
    private InstrumentDao instrumentDao;

    @PostConstruct
    public void initDB() {

        logger.info("Starting database initialization ...");

        Instrument[] instruments = new Instrument[]{
                new InstrumentBuilder().setInstrumentId("Drums").build(),
                new InstrumentBuilder().setInstrumentId("Guitar").build(),
                new InstrumentBuilder().setInstrumentId("Piano").build(),
                new InstrumentBuilder().setInstrumentId("Synthesizer").build(),
                new InstrumentBuilder().setInstrumentId("Voice").build()
        };
        saveToDB(instruments, i -> instrumentDao.save(i));

        List<Singer> singers = new ArrayList<>();

        Singer singer = new SingerBuilder()
                .setFirstName("John")
                .setLastName("Mayer")
                .setBirthDate(new Date(
                        new GregorianCalendar(1977, 9, 16)
                                .getTime().getTime()))
                .build();
        singer.addAlbum(new AlbumBuilder()
                .setTitle("The Search For Everything")
                .setReleaseDate(new Date(
                        new GregorianCalendar(2017, 1, 20)
                                .getTime().getTime()))
                .build());
        singer.addAlbum(new AlbumBuilder()
                .setTitle("Battle Stuidies")
                .setReleaseDate(new Date(
                        new GregorianCalendar(2009, 11, 17)
                                .getTime().getTime()))
                .build());
        singers.add(singer);

        singer = new SingerBuilder()
                .setFirstName("Eric")
                .setLastName("Clapton")
                .setBirthDate(new Date(
                        new GregorianCalendar(1945, 3, 30)
                                .getTime().getTime()))
                .build();
        singer.addAlbum(new AlbumBuilder()
                .setTitle("From The Cradle")
                .setReleaseDate(new Date(
                        new GregorianCalendar(1994, 9, 13)
                                .getTime().getTime()))
                .build());
        singers.add(singer);

        singers.add(new SingerBuilder()
                .setFirstName("John")
                .setLastName("Butler")
                .setBirthDate(new Date(
                        new GregorianCalendar(1975, 4, 1)
                                .getTime().getTime()))
                .build());

        saveToDB(singers.toArray(new Singer[singers.size()]), s -> singerDao.save(s));

        logger.info("Database initialization finished.");
    }

    private <T> void saveToDB(T[] elements, Consumer<T> consumer) {
        Arrays.stream(elements)
                .forEach(i -> consumer.accept(i));

    }
}
