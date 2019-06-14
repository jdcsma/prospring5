package jun.prospring5.ch12.configuration;

import jun.prospring5.ch12.common.service.SingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;

@Configuration
public class HttpInvokerConfiguration {

    @Autowired
    private SingerService singerService;

    @Bean(name = "/httpInvoker/singerService")
    public HttpInvokerServiceExporter httpInvokerServiceExporter() {
        HttpInvokerServiceExporter exporter =
                new HttpInvokerServiceExporter();
        exporter.setService(singerService);
        exporter.setServiceInterface(SingerService.class);
        return exporter;
    }
}
