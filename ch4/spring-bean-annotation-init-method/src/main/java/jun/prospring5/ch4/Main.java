package jun.prospring5.ch4;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext appContext
                = new AnnotationConfigApplicationContext(
                        InitMethodConfiguration.class);

        getBean("singerOne", appContext);
        getBean("singerTwo", appContext);
        getBean("singerThree", appContext);
    }

    private static Singer getBean(String beanName, ApplicationContext applicationContext) {

        try {
            Singer bean = (Singer) applicationContext.getBean(beanName);
            System.out.println(bean);
            return bean;
        } catch (BeanCreationException e) {
            System.out.println("An error occurred in bean configuration: " +
                    e.getMessage());
            return null;
        }
    }
}
