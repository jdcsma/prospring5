package jun.prospring5.ch8.configuration;

import jun.prospring5.ch8.entity.AlbumBuilder;
import jun.prospring5.ch8.entity.Instrument;
import jun.prospring5.ch8.entity.InstrumentBuilder;
import jun.prospring5.ch8.entity.Singer;
import jun.prospring5.ch8.entity.SingerBuilder;
import jun.prospring5.ch8.service.InstrumentService;
import jun.prospring5.ch8.service.SingerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Service
public class DatabaseInitializer {

    private static Logger logger =
            LoggerFactory.getLogger(DatabaseInitializer.class);

    @Autowired
    private SingerService singerService;

    @Autowired
    private InstrumentService instrumentService;

    @PostConstruct
    public void initDB() {

        logger.info("Starting database initialization ...");

        List<Instrument> instruments =
                new ArrayList<>(Arrays.asList(
                        new InstrumentBuilder().setInstrumentId("Drums").build(),
                        new InstrumentBuilder().setInstrumentId("Guitar").build(),
                        new InstrumentBuilder().setInstrumentId("Piano").build(),
                        new InstrumentBuilder().setInstrumentId("Synthesizer").build(),
                        new InstrumentBuilder().setInstrumentId("Voice").build()));
        instruments.forEach(i -> instrumentService.save(i));

        List<Singer> singers = new ArrayList<>();

        Singer singer = new SingerBuilder()
                .setFirstName("John")
                .setLastName("Mayer")
                .setBirthDate(new GregorianCalendar(1977, 9, 16)
                        .getTime())
                .build();
        singer.addAlbum(new AlbumBuilder()
                .setTitle("The Search For Everything")
                .setReleaseDate(new GregorianCalendar(2017, 1, 20)
                        .getTime())
                .build());
        singer.addAlbum(new AlbumBuilder()
                .setTitle("Battle Stuidies")
                .setReleaseDate(new Date(
                        new GregorianCalendar(2009, 11, 17)
                                .getTime().getTime()))
                .build());
        singers.add(singer);

        singer.addInstrument(new InstrumentBuilder()
                .setInstrumentId("Guitar")
                .build());
        singer.addInstrument(new InstrumentBuilder()
                .setInstrumentId("Piano")
                .build());

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
        singer.addInstrument(new InstrumentBuilder()
                .setInstrumentId("Guitar")
                .build());

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
