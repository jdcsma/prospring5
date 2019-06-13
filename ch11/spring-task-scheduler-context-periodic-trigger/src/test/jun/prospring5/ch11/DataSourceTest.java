package jun.prospring5.ch11;

import jun.prospring5.ch11.service.CarService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.PeriodicTrigger;

public class DataSourceTest {

    private static Logger logger =
            LoggerFactory.getLogger(DataSourceTest.class);

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

        CarService carService = appContext.getBean(
                "carService", CarService.class);

        TaskScheduler scheduler = appContext.getBean(
                "carScheduler", TaskScheduler.class);

        waitingScheduledJobToEnd(carService);

        logger.info("Starting update with scheduler.schedule(Runnable, PeriodicTrigger) ...");

        carService.prepareUpdateCarAgeJob();

        // This code same as:
        //     <task:scheduled-tasks scheduler="carScheduler">
        //         <task:scheduled ref="carService" method="updateCarAgeJob" fixed-delay="3000"/>
        //     </task:scheduled-tasks>
        scheduler.schedule(carService::updateCarAgeJob,
                new PeriodicTrigger(3_000));

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
