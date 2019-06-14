package jun.prospring5.ch11;

import jun.prospring5.ch11.configuration.AppConfiguration;
import jun.prospring5.ch11.service.AsyncService;
import jun.prospring5.ch11.util.ThreadUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class AsyncTaskTest {

    private static Logger logger =
            LoggerFactory.getLogger(AsyncTaskTest.class);

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

        AsyncService asyncService = appContext.getBean(
                "asyncService", AsyncService.class);

        for (int i = 0; i < 5; ++i) {
            asyncService.asyncTask();
        }

        Future<String> result1 = asyncService.asyncWithReturn("1");
        Future<String> result2 = asyncService.asyncWithReturn("2");
        Future<String> result3 = asyncService.asyncWithReturn("3");

        ThreadUtils.sleep(6_000);

        try {
            logger.info("Result1 return value:" + result1.get());
            logger.info("Result2 return value:" + result2.get());
            logger.info("Result3 return value:" + result3.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    @After
    public void clear() {
        if (appContext != null) {
            appContext.close();
        }
    }

}
