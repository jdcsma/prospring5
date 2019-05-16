package jun.prospring5.ch4;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        GenericXmlApplicationContext appContext
                = new GenericXmlApplicationContext();
        appContext.refresh();

        ConfigurableEnvironment env = appContext.getEnvironment();
        MutablePropertySources propertySources = env.getPropertySources();

        Map<String, Object> appMap = new HashMap<>();
        appMap.put("user.home", "application_home");

//        propertySources.addLast(new MapPropertySource("prospring5 MAP", appMap));
        propertySources.addFirst(new MapPropertySource("prospring5 MAP", appMap));

        System.out.println("Print environment variables form System:");
        displayInfo("user.home: ", System.getProperty("user.home"));
        displayInfo("JAVA_HOME: ", System.getenv("JAVA_HOME"));
        displayInfo("classpath: ", System.getProperty("java.class.path"));

        System.out.println("Print environment variables form ConfigurableEnvironment:");
        displayInfo("user.home: ", env.getProperty("user.home"));
        displayInfo("JAVA_HOME: ", env.getProperty("JAVA_HOME"));
        displayInfo("classpath: ", env.getProperty("java.class.path"));

        appContext.close();
    }

    private static void displayInfo(String name, String info) {

        System.out.println("  name: " + name);
        String[] strings = info.split(";");
        Stream.of(strings).forEach(
                i -> {
                    System.out.println("    " + i);
                }
        );
    }
}
