package jun.prospring5.ch4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("message.properties")
public class PropertySourceConfiguration {

    @Autowired
    private Environment env;

    @Bean("printer")
    public PropertySourcePrinter getPrinter() {
        PropertySourcePrinter printer =
                new PropertySourcePrinter(
                        this.env.getProperty("message"));
        return printer;
    }
}
