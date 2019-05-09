package jun.prospring5.ch3;

import org.springframework.context.support.GenericXmlApplicationContext;

public class Main {

    public static void main(String[] args) {

        GenericXmlApplicationContext
                parentContext = new GenericXmlApplicationContext();
        parentContext.load("app-context-xml-parent.xml");
        parentContext.refresh();

        GenericXmlApplicationContext
                childContext = new GenericXmlApplicationContext();
        childContext.load("app-context-xml-child.xml");
        childContext.setParent(parentContext);
        childContext.refresh();

        StringHolder sh1 = (StringHolder) childContext.getBean("stringHolder1");
        System.out.println(sh1.toString());
        StringHolder sh2 = (StringHolder) childContext.getBean("stringHolder2");
        System.out.println(sh2.toString());
        StringHolder sh3 = (StringHolder) childContext.getBean("stringHolder3");
        System.out.println(sh3.toString());
    }
}
