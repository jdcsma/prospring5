package jun.prospring5.ch10.configuration;

import jun.prospring5.ch10.converter.StringToDateTimeConverter;
import jun.prospring5.ch10.entity.Singer;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.convert.converter.Converter;

import java.util.HashSet;
import java.util.Set;

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

    @Bean
    public Singer john(
            @Value("${countrySinger.firstName}") String firstName,
            @Value("${countrySinger.lastName}") String lastName,
            @Value("${countrySinger.birthDate}") DateTime birthDate) {
        Singer singer = new Singer();
        singer.setFirstName(firstName);
        singer.setLastName(lastName);
        singer.setBirthDate(birthDate);
        return singer;
    }

    @Bean
    public ConversionServiceFactoryBean conversionService() {
        ConversionServiceFactoryBean factoryBean =
                new ConversionServiceFactoryBean();
        Set<Converter<?, ?>> converters = new HashSet<>();
        converters.add(converter());
        factoryBean.setConverters(converters);
        factoryBean.afterPropertiesSet();
        return factoryBean;
    }

    @Bean
    public StringToDateTimeConverter converter() {
        StringToDateTimeConverter converter = new StringToDateTimeConverter();
        converter.setDatePattern(dateTimePattern);
        converter.init();
        return converter;
    }
}
