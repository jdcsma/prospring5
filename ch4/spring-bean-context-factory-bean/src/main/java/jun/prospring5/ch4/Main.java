package jun.prospring5.ch4;

import org.springframework.context.support.GenericXmlApplicationContext;

import java.security.MessageDigest;

public class Main {

    public static void main(String[] args) {

        GenericXmlApplicationContext appContext
                = new GenericXmlApplicationContext();
        appContext.load("app-context-xml.xml");
        appContext.refresh();

        MessageDigester digester =
                (MessageDigester) appContext.getBean(
                        "digester");

        digester.digest("Hi, FactoryBean.");

        MessageDigestFactoryBean factoryBean =
                (MessageDigestFactoryBean) appContext.getBean(
                        "&shaDigest");
        try {
            System.out.println("Using FactoryBean:");
            MessageDigest digest = factoryBean.getObject();
            System.out.println("Using algorithm:" + digest.getAlgorithm());
            byte[] bytes = "Hi, FactoryBean.".getBytes();
            System.out.println(MessageDigester.byte2Hex(digest.digest(bytes)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
