package jun.prospring5.ch3;

import org.springframework.context.support.GenericXmlApplicationContext;

public class Main {

    public static void main(String[] args) {

        GenericXmlApplicationContext appContext =
                new GenericXmlApplicationContext();

        appContext.load("app-context-xml.xml");
        appContext.refresh();

        TargetBean tb = (TargetBean)
                appContext.getBean("targetBean");
        System.out.println(tb.getInjectedBean().getName());

        TargetBean2 tb2 = (TargetBean2)
                appContext.getBean("targetBean2");
        System.out.println(tb2.getInjectedBean().getName());
    }
}
