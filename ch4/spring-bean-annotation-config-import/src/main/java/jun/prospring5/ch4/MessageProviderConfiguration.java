package jun.prospring5.ch4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("message.properties")
public class MessageProviderConfiguration {

    @Autowired
    private Environment env;

    @Bean("provider")
    @Lazy
    public MessageProvider getMessageProvider() {
        MessageProvider provider =
                new MessageProvider(
                        this.env.getProperty("message"));
        return provider;
    }
}
