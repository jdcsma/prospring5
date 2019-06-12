package jun.prospring5.ch10;

import jun.prospring5.ch10.configuration.AppConfiguration;
import jun.prospring5.ch10.entity.Genre;
import jun.prospring5.ch10.entity.Singer;
import jun.prospring5.ch10.service.SingerValidationService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class BeanValidationTest {

    private static Logger logger =
            LoggerFactory.getLogger(BeanValidationTest.class);

    private GenericApplicationContext appContext;

    @Before
    public void setup() {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(
                        AppConfiguration.class);

        appContext = context;
    }

    @Test
    public void test() {

        SingerValidationService validationService =
                appContext.getBean("singerValidationService",
                        SingerValidationService.class);

        Singer singer = new Singer();
        singer.setFirstName("J");
        singer.setLastName("Mayer");
        singer.setGenre(Genre.COUNTRY);
        singer.setGender(null);

        validateSinger(singer, validationService);
    }

    @After
    public void clean() {
        if (appContext != null) {
            appContext.close();
        }
    }

    private static void validateSinger(
            Singer singer, SingerValidationService validationService) {
        Set<ConstraintViolation<Singer>> violations =
                validationService.validateSinger(singer);
        listViolations(violations);
    }

    private static void listViolations(
            Set<ConstraintViolation<Singer>> violations) {
        logger.info("No. of violations:" + violations.size());
        for (ConstraintViolation<Singer> violation : violations) {
            logger.info("Validation error for property:" + violation.getPropertyPath()
            + " with value:" + violation.getInvalidValue()
            + " with error message:" + violation.getMessage());
        }
    }
}
