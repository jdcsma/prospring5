package jun.prospring5.ch3;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.StopWatch;

public class Main {

    public static void main(String[] args) {

        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(LookupConfig.class);

        DemoBean standardLookupBean = (DemoBean)
                applicationContext.getBean("standardLookupBean");
        DemoBean abstractLookupBean = (DemoBean)
                applicationContext.getBean("abstractLookupBean");

        displayInfo("standardLookupBean", standardLookupBean);
        displayInfo("abstractLookupBean", abstractLookupBean);
    }

    public static void displayInfo(String beanName, DemoBean bean) {

        SomeObject so1 = bean.getObject();
        SomeObject so2 = bean.getObject();

        System.out.println("" + beanName +
                ": SomeObject instance the same? " +
                (so1 == so2)
        );

        StopWatch sw = new StopWatch();
        sw.start("lookupDemo");
        for (int i = 0; i < 100000; i++) {
            SomeObject so = bean.getObject();
            so.doSomething();
        }
        sw.stop();

        System.out.println("100000 gets took " +
                sw.getTotalTimeMillis() + " ms");
    }
}
