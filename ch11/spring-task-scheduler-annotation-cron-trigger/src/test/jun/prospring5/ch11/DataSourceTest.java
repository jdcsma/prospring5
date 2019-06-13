package jun.prospring5.ch11;

import jun.prospring5.ch11.configuration.AppConfiguration;
import jun.prospring5.ch11.configuration.JpaConfiguration;
import jun.prospring5.ch11.service.CarService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;

public class DataSourceTest {

    private static Logger logger =
            LoggerFactory.getLogger(DataSourceTest.class);

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

        CarService carService = appContext.getBean(
                "carService", CarService.class);


        TaskScheduler scheduler = appContext.getBean(
                "carScheduler", TaskScheduler.class);

        waitingScheduledJobToEnd(carService);

        logger.info("Starting update with scheduler.schedule(Runnable, CronTrigger) ...");

        carService.prepareUpdateCarAgeJob();

        // This code same as:
        //     <task:scheduled-tasks scheduler="carScheduler">
        //         <task:scheduled ref="carService" method="updateCarAgeJob" cron="0 * * * * *"/>
        //     </task:scheduled-tasks>
        scheduler.schedule(carService::updateCarAgeJob,
                new CronTrigger("0 * * * * *"));

        waitingScheduledJobToEnd(carService);
    }

    @After
    public void clear() {
        if (appContext != null) {
            appContext.close();
        }
    }

    public void waitingScheduledJobToEnd(CarService carService) {
        while (!carService.isDone()) {
            logger.info("Waiting for scheduled job to end ...");
            sleep();
        }
    }

    public void sleep() {
        try {
            Thread.sleep(3_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
