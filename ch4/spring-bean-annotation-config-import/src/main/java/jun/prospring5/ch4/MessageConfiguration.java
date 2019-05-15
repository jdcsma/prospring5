package jun.prospring5.ch4;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        MessageProviderConfiguration.class,
        MessageRendererConfiguration.class
})
public class MessageConfiguration {
}
