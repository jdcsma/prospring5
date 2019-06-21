package jun.prospring5.ch12.configuration;

import jun.prospring5.ch12.common.factory.HttpMessageConverterFactory;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
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
    public Credentials credentials() {
        return new UsernamePasswordCredentials(
                "prospring5", "prospring5");
    }

    @Bean
    public CredentialsProvider provider(Credentials credentials) {
        BasicCredentialsProvider provider = new BasicCredentialsProvider();
        provider.setCredentials(AuthScope.ANY, credentials);
        return provider;
    }

    @Bean
    public HttpComponentsClientHttpRequestFactory
    httpRequestFactory(CredentialsProvider provider) {
        HttpComponentsClientHttpRequestFactory factory =
                new HttpComponentsClientHttpRequestFactory();
        HttpClient client = HttpClientBuilder.create()
                .setDefaultCredentialsProvider(provider)
                .build();
        factory.setHttpClient(client);
        return factory;
    }

    @Bean
    public RestTemplate restTemplate(
            CastorMarshaller castorMarshaller, CredentialsProvider provider) {
        RestTemplate template = new RestTemplate(httpRequestFactory(provider));
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
