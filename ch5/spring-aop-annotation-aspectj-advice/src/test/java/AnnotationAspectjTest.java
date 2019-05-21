import jun.prospring5.ch5.AnnotationAspectjConfiguration;
import jun.prospring5.ch5.Documentarist;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class AnnotationAspectjTest {

    @Test
    public void xmlTest() {

        System.out.println("--------------- xmlTest ---------------");

        GenericXmlApplicationContext appContext =
                new GenericXmlApplicationContext(
                        "classpath:app-context-xml.xml");

        Documentarist documentarist = appContext.getBean(
                "documentarist", Documentarist.class);

        System.out.println("Documentarist >>");
        documentarist.execute();
    }

    @Test
    public void annotationTest() {

        System.out.println("--------------- annotationTest ---------------");

        AnnotationConfigApplicationContext appContext =
                new AnnotationConfigApplicationContext(
                        AnnotationAspectjConfiguration.class);

        Documentarist documentarist = appContext.getBean(
                "documentarist", Documentarist.class);

        System.out.println("Documentarist >>");
        documentarist.execute();
    }
}
