package jun.prospring5.ch4;

import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.Locale;

public class Main {

    public static void main(String[] args) {

        GenericXmlApplicationContext appContext
                = new GenericXmlApplicationContext();
        appContext.load("app-context-xml.xml");
        appContext.refresh();

        Locale en = new Locale("en", "US");
        Locale zh = new Locale("zh", "CN");
        String enString = appContext.getMessage("msg", null, en);
        String zhString = appContext.getMessage("msg", null, zh);

        System.out.println("enString: msg:" + enString);
        System.out.println("zhString: msg:" + zhString);

        enString = appContext.getMessage("nameMsg", new Object[]{"John", "Mayer"}, en);
        zhString = appContext.getMessage("nameMsg", new Object[]{"小", "明"}, zh);

        System.out.println("enString: nameMsg:" + enString);
        System.out.println("zhString: nameMsg:" + zhString);

        appContext.close();
    }
}
