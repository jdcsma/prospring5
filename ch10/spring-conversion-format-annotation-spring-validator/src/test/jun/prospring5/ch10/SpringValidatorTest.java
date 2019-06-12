package jun.prospring5.ch10;

import jun.prospring5.ch10.configuration.AppConfiguration;
import jun.prospring5.ch10.entity.Singer;
import jun.prospring5.ch10.validation.SingerValidator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.List;

public class SpringValidatorTest {

    private static Logger logger =
            LoggerFactory.getLogger(SpringValidatorTest.class);

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

        Singer singer = new Singer();
        singer.setFirstName(null);
        singer.setLastName("Mayer");

        Validator validator = appContext.getBean(
                "singerValidator", SingerValidator.class);

        BeanPropertyBindingResult result =
                new BeanPropertyBindingResult(
                        singer, "John");

        ValidationUtils.invokeValidator(validator, singer, result);

        List<ObjectError> errors = result.getAllErrors();
        logger.info("No. of validation errors:" + errors.size());
        errors.forEach(e -> {
            logger.info("code:" + e.getCode());
            logger.info("  detail:" + e.toString());
        });
    }

    @After
    public void clean() {
        if (appContext != null) {
            appContext.close();
        }
    }
}
