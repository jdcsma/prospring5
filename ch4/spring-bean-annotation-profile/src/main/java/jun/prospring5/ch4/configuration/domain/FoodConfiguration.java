package jun.prospring5.ch4.configuration.domain;

import jun.prospring5.ch4.domain.Food;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class FoodConfiguration {

    @Bean
    @Scope("prototype")
    public Food food() {
        return new Food();
    }
}
