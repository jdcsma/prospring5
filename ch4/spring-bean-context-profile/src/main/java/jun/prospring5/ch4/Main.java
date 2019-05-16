package jun.prospring5.ch4;

import jun.prospring5.ch4.service.FoodProviderService;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Main {

    public static void main(String[] args) {

        GenericXmlApplicationContext appContext
                = new GenericXmlApplicationContext();

        appContext.load("classpath:app-context-xml-*.xml");
        appContext.refresh();

        FoodProviderService fps =
                (FoodProviderService) appContext.getBean(
                        "foodProviderService");

        fps.provideLunchSet().stream()
                .forEach(f -> System.out.println(f));

        appContext.close();
    }
}
