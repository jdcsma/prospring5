package jun.prospring5.ch12.configuration;

import jun.prospring5.ch12.common.factory.HttpMessageConverterFactory;
import org.apache.http.HttpMessage;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.oxm.castor.CastorMappingException;
import org.springframework.oxm.castor.CastorMarshaller;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@ComponentScan(basePackages = {"jun.prospring5.ch12"},
        excludeFilters = {@ComponentScan.Filter(
                type = FilterType.ANNOTATION, value = {Configuration.class})})
public class AppConfiguration {

    @Autowired
    private ApplicationContext context;

    @Bean
    public HttpComponentsClientHttpRequestFactory httpRequestFactory() {
        HttpComponentsClientHttpRequestFactory factory =
                new HttpComponentsClientHttpRequestFactory();
        HttpClient client = HttpClientBuilder.create().build();
        factory.setHttpClient(client);
        return factory;
    }

    @Bean
    public RestTemplate restTemplate(CastorMarshaller castorMarshaller) {
        RestTemplate template = new RestTemplate(httpRequestFactory());
        List<HttpMessageConverter<?>> converters = new ArrayList<>();
        try {
            converters.add(HttpMessageConverterFactory
                    .newJsonHttpMessageConverter(
                            HttpMessageConverterFactory.newJsonObjectMapper()));
            converters.add(HttpMessageConverterFactory
                    .newXmlHttpMessageConverter(castorMarshaller));
        } catch (IOException e) {
            e.printStackTrace();
        }
        template.setMessageConverters(converters);
        return template;
    }

    @Bean
    public CastorMarshaller castorMarshaller()
            throws CastorMappingException, IOException {
        return HttpMessageConverterFactory.newCastorMarshaller(
                context, "classpath:oxm-mapping.xml");
    }
}
