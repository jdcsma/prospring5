package jun.prospring5.ch10.configuration;

import jun.prospring5.ch10.conversion.ApplicationConversionServiceFactoryBean;
import jun.prospring5.ch10.entity.Singer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import java.text.ParseException;
import java.util.Locale;

@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = {"jun.prospring5.ch10"})
public class AppConfiguration {

    @Value("${date.format.pattern}")
    private String dateTimePattern;

    @Bean
    public static PropertySourcesPlaceholderConfigurer
    propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Autowired
    private ApplicationConversionServiceFactoryBean conversionService;

    @Bean
    public Singer john() {
        Singer singer = new Singer();
        singer.setFirstName("John");
        singer.setLastName("Mayer");
        try {
            singer.setBirthDate(conversionService
                    .getDateTimeFormatter()
                    .parse("1977-10-16", Locale.ENGLISH));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return singer;
    }


}
