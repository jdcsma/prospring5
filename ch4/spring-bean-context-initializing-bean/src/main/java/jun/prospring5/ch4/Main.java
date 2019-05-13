package jun.prospring5.ch4;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Main {

    public static void main(String[] args) {

        GenericXmlApplicationContext appContext
                = new GenericXmlApplicationContext();
        appContext.load("app-context-xml.xml");
        appContext.refresh();

        getBean("singerOne", appContext);
        getBean("singerTwo", appContext);
        getBean("singerThree", appContext);
    }

    private static SingerWithInitializingBean getBean(String beanName, ApplicationContext applicationContext) {

        try {
            SingerWithInitializingBean bean = (SingerWithInitializingBean) applicationContext.getBean(beanName);
            System.out.println(bean);
            return bean;
        } catch (BeanCreationException e) {
            System.out.println("An error occurred in bean configuration: " +
                    e.getMessage());
            return null;
        }
    }
}
