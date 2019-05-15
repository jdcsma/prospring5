package jun.prospring5.ch4;

import org.springframework.context.support.GenericXmlApplicationContext;

public class Main {

    public static void main(String[] args) {

        GenericXmlApplicationContext appContext
                = new GenericXmlApplicationContext();
        appContext.load("app-context-xml.xml");
        appContext.refresh();

        CustomEditorExample bean =
                (CustomEditorExample) appContext.getBean(
                        "exampleBean");

        System.out.println(bean.getFullName().toString());

        appContext.close();
    }
}
