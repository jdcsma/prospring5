package jun.prospring5.ch3;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.util.StopWatch;

public class Main {

    public static void main(String[] args) {
        GenericXmlApplicationContext
                applicationContext = new GenericXmlApplicationContext();
        applicationContext.load("app-context-xml.xml");
        applicationContext.refresh();

        ReplacementTarget replacementTarget = (ReplacementTarget)
                applicationContext.getBean("replacementTarget");
        ReplacementTarget standardTarget = (ReplacementTarget)
                applicationContext.getBean("standardTarget");

        displayInfo(replacementTarget);
        displayInfo(standardTarget);
    }

    private static void displayInfo(ReplacementTarget rt) {
        System.out.println(rt.formatMessage("Thanks for playing, try again!"));

        StopWatch sw = new StopWatch();
        sw.start("test");
        for (int i = 0; i < 1000000; i++) {
            String out = rt.formatMessage("No filter in my head");
//            System.out.println(out);
        }
        sw.stop();

        System.out.println("1000000 invocations took: " + sw.getTotalTimeMillis() + " ms");
    }
}
