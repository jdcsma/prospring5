package jun.prospring5.ch10;

import jun.prospring5.ch10.entity.Singer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class PropertyEditorTest {

    private static Logger logger =
            LoggerFactory.getLogger(PropertyEditorTest.class);

    private GenericApplicationContext appContext;

    @Before
    public void setup() {

        GenericXmlApplicationContext context =
                new GenericXmlApplicationContext();
        context.load("classpath:app-context-xml.xml");
        context.refresh();

        appContext = context;
    }

    @Test
    public void test() {

        Singer singerA = (Singer) appContext.getBean("eric");
        logger.info("eric info:" + singerA);
        Singer singerB = (Singer) appContext.getBean("countrySinger");
        logger.info("john info:" + singerB);
    }

    @After
    public void clean() {
        if (appContext != null) {
            appContext.close();
        }
    }
}
