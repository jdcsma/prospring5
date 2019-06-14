package jun.prospring5.ch11.business.job;

import org.joda.time.DateTime;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class QuartzJob implements Job {

    private static Object monitor = new Object();
    private static volatile int executedCount = 0;

    @Override
    public void execute(JobExecutionContext context)
            throws JobExecutionException {

        synchronized (monitor) {
            ++executedCount;
        }

        System.out.println(DateTime.now().toString(
                "yyyy-MM-dd HH:mm:ss.SSS") +
                " executed count:" + executedCount +
                " on thread:" + Thread.currentThread().getName());
    }

    public static int getExecutedCount() {
        return executedCount;
    }
}
