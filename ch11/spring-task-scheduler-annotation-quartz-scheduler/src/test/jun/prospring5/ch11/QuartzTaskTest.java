package jun.prospring5.ch11;

import jun.prospring5.ch11.business.job.QuartzJob;
import jun.prospring5.ch11.configuration.AppConfiguration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

public class QuartzTaskTest {

    private static Logger logger =
            LoggerFactory.getLogger(QuartzTaskTest.class);

    private GenericApplicationContext appContext;

    private Scheduler quartzScheduler;

    @Before
    public void setup() throws SchedulerException {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(
                        AppConfiguration.class);

        appContext = context;

        quartzScheduler = appContext.getBean(
                "quartzScheduler", Scheduler.class);

        quartzScheduler.start();
    }

    @Test
    public void test() throws
            SchedulerException, InterruptedException {

        JobDetail jobDetail = JobBuilder
                .newJob(QuartzJob.class)
                .withIdentity("jobName", "groupName")
                .build();

        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity("triggerName", "groupName")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInMilliseconds(1_000)
                        .repeatForever())
                .startNow()
                .build();

        quartzScheduler.scheduleJob(jobDetail, trigger);

        while (QuartzJob.getExecutedCount() < 2) {
            Thread.sleep(1_000);
        }
    }

    @After
    public void clear() throws SchedulerException {

        if (quartzScheduler != null) {
            quartzScheduler.shutdown();
        }

        if (appContext != null) {
            appContext.close();
        }
    }

}
