package jun.prospring5.ch11.configuration;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "jun.prospring5.ch11")
public class AppConfiguration {

    @Bean
    public Scheduler quartzScheduler() throws SchedulerException {
        return new StdSchedulerFactory().getScheduler();
    }
}
