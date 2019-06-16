package jun.prospring5.ch12.configuration;

import org.hornetq.api.core.TransportConfiguration;
import org.hornetq.core.remoting.impl.netty.NettyConnectorFactory;
import org.hornetq.core.remoting.impl.netty.TransportConstants;
import org.hornetq.jms.client.HornetQJMSConnectionFactory;
import org.hornetq.jms.client.HornetQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

import javax.jms.ConnectionFactory;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableJms
@ComponentScan(basePackages = "jun.prospring5.ch12")
public class AppConfiguration {

    // Target
    @Bean
    public HornetQQueue prospring5() {
        return new HornetQQueue("prospring5");
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        Map<String, Object> details = new HashMap<>();
        details.put(TransportConstants.HOST_PROP_NAME, "127.0.0.1");
        details.put(TransportConstants.PORT_PROP_NAME, "5455");
        TransportConfiguration configuration = new TransportConfiguration(
                NettyConnectorFactory.class.getName(), details);
        return new HornetQJMSConnectionFactory(false, configuration);
    }

    // Consumer (depend on ConnectionFactory)
    @Bean
    public JmsListenerContainerFactory<DefaultMessageListenerContainer>
    jmsListenerContainerFactory() {
        DefaultJmsListenerContainerFactory factory =
                new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        factory.setConcurrency("3-5");
        return factory;
    }

    // Producer (depend on ConnectionFactory)
    @Bean
    public JmsTemplate jmsTemplate() {
        JmsTemplate template = new JmsTemplate(connectionFactory());
        template.setDefaultDestination(prospring5());
        return template;
    }
}
