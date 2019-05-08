package jun.prospring5.ch3;

import java.io.IOException;
import java.util.Properties;

public class MessageSupportFactory {

    public static MessageRenderer getMessageRenderer() {

        Properties properties = new Properties();

        try {
            properties.load(properties.getClass().
                    getResourceAsStream("/sac.properties"));

            String rendererClass = properties.getProperty("renderer.class");
            String providerClass = properties.getProperty("provider.class");

            MessageRenderer renderer = (MessageRenderer)
                    Class.forName(rendererClass).newInstance();
            MessageProvider provider = (MessageProvider)
                    Class.forName(providerClass).newInstance();

            renderer.setMessageProvider(provider);

            return renderer;
        } catch (IOException | ClassNotFoundException |
                IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }

        return null;
    }
}
