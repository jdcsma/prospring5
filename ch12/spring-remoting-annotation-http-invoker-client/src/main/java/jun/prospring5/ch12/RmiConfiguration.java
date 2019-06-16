package jun.prospring5.ch12;

import jun.prospring5.ch12.common.service.SingerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;

@Configuration
public class RmiConfiguration {

    @Bean
    public SingerService singerService() {
        HttpInvokerProxyFactoryBean factory =
                new HttpInvokerProxyFactoryBean();
        factory.setServiceInterface(SingerService.class);
        factory.setServiceUrl("http://localhost:8080/invoker/httpInvoker/singerService");
        factory.afterPropertiesSet();
        return (SingerService) factory.getObject();
    }
}
