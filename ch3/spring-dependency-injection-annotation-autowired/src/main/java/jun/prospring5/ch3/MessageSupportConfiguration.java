package jun.prospring5.ch3;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageSupportConfiguration {

    @Bean
    public MessageProvider messageProvider() {
        return new MessageProviderText();
    }

    @Bean
    public MessageRenderer messageRenderer() {
        return new MessageRendererStandardOutput();
    }
}
