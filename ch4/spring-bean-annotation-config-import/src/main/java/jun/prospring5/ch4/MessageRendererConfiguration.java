package jun.prospring5.ch4;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
public class MessageRendererConfiguration {

    @Bean("renderer")
    @DependsOn("provider")
    public MessageRenderer getMessageProvider(MessageProvider provider) {
        MessageRenderer renderer = new MessageRenderer(provider);
        return renderer;
    }
}
