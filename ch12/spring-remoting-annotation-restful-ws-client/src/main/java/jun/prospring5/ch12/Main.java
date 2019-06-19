package jun.prospring5.ch12;

import jun.prospring5.ch12.common.domain.Singers;
import jun.prospring5.ch12.common.entity.Singer;
import jun.prospring5.ch12.common.entity.SingerBuilder;
import jun.prospring5.ch12.configuration.AppConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.client.RestTemplate;

import java.util.GregorianCalendar;

public class Main {

    private static final Logger logger =
            LoggerFactory.getLogger(Main.class);

    private static final String URL_ROOT = "http://localhost:8080/singer";
    private static final String URL_GET_ALL_SINGERS = URL_ROOT + "/listdata";
    private static final String URL_GET_SINGER_BY_ID = URL_ROOT + "/id/{id}";
    private static final String URL_GET_SINGER_BY_FIRST_NAME = URL_ROOT + "/firstName/{firstName}";
    private static final String URL_CREATE_SINGER = URL_ROOT + "/";
    private static final String URL_UPDATE_SINGER = URL_ROOT + "/id/{id}";
    private static final String URL_DELETE_SINGER = URL_ROOT + "/id/{id}";

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(
                        AppConfiguration.class);

        RestTemplate restTemplate = context.getBean(
                "restTemplate", RestTemplate.class);

        logger.info(">>> Testing retrieve all singers:");
        Singers singers = restTemplate.getForObject(
                URL_GET_ALL_SINGERS, Singers.class);
        listSingers(singers);

        logger.info(">>> Testing retrieve singer by id:1");
        Singer findSingerByIdResult = restTemplate.getForObject(
                URL_GET_SINGER_BY_ID, Singer.class, 1);
        logger.info("    result:" + findSingerByIdResult);

        logger.info(">>> Testing retrieve singer by first name:");
        Singers findSingerByFirstNameResult = restTemplate.getForObject(
                URL_GET_SINGER_BY_FIRST_NAME, Singers.class, "John");
        logger.info("    result:" + findSingerByFirstNameResult);

        logger.info(">>> Testing update singer by id:1");
        Singer singer = restTemplate.getForObject(URL_UPDATE_SINGER, Singer.class, 1);
        singer.setFirstName("Ed");
        restTemplate.put(URL_UPDATE_SINGER, singer, 1);
        logger.info("   update singer successfully:" + singer);

        logger.info(">>> Testing delete singer by id:1");
        restTemplate.delete(URL_DELETE_SINGER, 1);
        singers = restTemplate.getForObject(URL_GET_ALL_SINGERS, Singers.class);
        listSingers(singers);

        logger.info(">>> Testing create singer:");
        Singer newSinger = new SingerBuilder()
                .setFirstName("Kurt")
                .setLastName("Cobain")
                .setBirthDate(new GregorianCalendar(
                        1967, 2, 20).getTime())
                .build();
        newSinger = restTemplate.postForObject(
                URL_CREATE_SINGER, newSinger, Singer.class);
        logger.info("   create singer successfully:" + newSinger);

        singers = restTemplate.getForObject(URL_GET_ALL_SINGERS, Singers.class);
        listSingers(singers);

        context.close();
    }

    private static void listSingers(Singers singers) {
        singers.getSingers().forEach(s -> logger.info("   singer:" + s.toString()));
    }
}
