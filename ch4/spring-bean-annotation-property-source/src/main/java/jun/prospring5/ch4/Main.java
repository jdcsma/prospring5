package jun.prospring5.ch4;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext appContext
                = new AnnotationConfigApplicationContext(
                        PropertySourceConfiguration.class);

        PropertySourcePrinter printer =
                (PropertySourcePrinter) appContext.getBean(
                        "printer");
        printer.print();

        appContext.close();
    }
}
