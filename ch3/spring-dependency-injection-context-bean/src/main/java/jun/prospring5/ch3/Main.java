package jun.prospring5.ch3;

import org.springframework.context.annotation.Bean;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Main {

    public static void main(String[] args) {

        GenericXmlApplicationContext appContext =
                new GenericXmlApplicationContext();

        appContext.load("app-context-xml.xml");
        appContext.refresh();

        InjectBean ib = (InjectBean)
                appContext.getBean("injectBean");
        TargetBean tb = (TargetBean)
                appContext.getBean("targetBean");

        System.out.println(tb.getInjectedBean().getName());
    }
}
