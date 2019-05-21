package jun.prospring5.ch5;

import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("bean.properties")
public class ProxyFactoryBeanConfiguration {

    @Autowired
    private Environment env;

    @Bean
    public Contact contact() {
        Contact contact = new Contact();
        contact.setName(this.env.getProperty(
                "bean.contact.name"));
        return contact;
    }

    @Bean
    public Advisor advisor() {
        return new IsModifiedAdvisor();
    }

    @Bean
    public ProxyFactoryBean bean() {

        ProxyFactoryBean bean = new ProxyFactoryBean();
        bean.setTargetName("contact");
        bean.setInterceptorNames("advisor");
        bean.setProxyTargetClass(true);

        return bean;
    }
}
