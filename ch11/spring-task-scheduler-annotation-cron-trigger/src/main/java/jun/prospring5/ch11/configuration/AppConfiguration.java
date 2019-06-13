package jun.prospring5.ch11.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@Import(JpaConfiguration.class)
@EnableScheduling
@ComponentScan(basePackages = "jun.prospring5.ch11")
public class AppConfiguration {
}
