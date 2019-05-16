package jun.prospring5.ch4.configuration.service.kindergarten;

import jun.prospring5.ch4.service.FoodProviderService;
import jun.prospring5.ch4.service.kindergarten.FoodProviderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("kindergarten")
public class FoodProviderServiceConfiguration {

    @Bean
    public FoodProviderService foodProviderService() {
        return new FoodProviderServiceImpl();
    }
}
