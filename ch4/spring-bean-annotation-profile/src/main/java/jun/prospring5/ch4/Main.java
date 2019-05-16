package jun.prospring5.ch4;

import jun.prospring5.ch4.configuration.domain.FoodConfiguration;
import jun.prospring5.ch4.service.FoodProviderService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {

        final Class<?>[] configurations = new Class[]{
                FoodConfiguration.class,
                jun.prospring5.ch4.configuration.service.
                        highschool.FoodProviderServiceConfiguration.class,
                jun.prospring5.ch4.configuration.service.
                        kindergarten.FoodProviderServiceConfiguration.class
        };

        AnnotationConfigApplicationContext appContext
                = new AnnotationConfigApplicationContext(configurations);

        FoodProviderService fps =
                (FoodProviderService) appContext.getBean(
                        "foodProviderService");

        fps.provideLunchSet().stream()
                .forEach(f -> System.out.println(f));

        appContext.close();
    }
}
