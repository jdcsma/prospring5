package jun.prospring5.ch4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("message.properties")
public class MessageProviderConfiguration {

    @Autowired
    private Environment env;

    @Bean("provider")
    public MessageProvider getMessageProvider() {
        return new MessageProvider(this.env.getProperty("message"));
    }
}
