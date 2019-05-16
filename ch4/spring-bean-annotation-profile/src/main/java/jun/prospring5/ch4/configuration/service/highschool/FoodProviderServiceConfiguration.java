package jun.prospring5.ch4.configuration.service.highschool;

import jun.prospring5.ch4.service.FoodProviderService;
import jun.prospring5.ch4.service.highschool.FoodProviderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("highschool")
public class FoodProviderServiceConfiguration {

    @Bean
    public FoodProviderService foodProviderService() {
        return new FoodProviderServiceImpl();
    }
}
