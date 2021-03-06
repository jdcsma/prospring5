package jun.prospring5.ch10;

import jun.prospring5.ch10.configuration.AppConfiguration;
import jun.prospring5.ch10.entity.Singer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.convert.ConversionService;

public class ConversionServiceTest {

    private static Logger logger =
            LoggerFactory.getLogger(ConversionServiceTest.class);

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

        Singer singer = appContext.getBean("john", Singer.class);
        logger.info("singer info:" + singer);

        ConversionService conversionService = appContext.getBean(
                "conversionService", ConversionService.class);
        logger.info("Birth date of singer is : " +
                conversionService.convert(
                        singer.getBirthDate(), String.class));
    }

    @After
    public void clean() {
        if (appContext != null) {
            appContext.close();
        }
    }
}
