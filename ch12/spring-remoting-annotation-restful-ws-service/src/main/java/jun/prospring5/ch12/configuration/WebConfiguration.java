package jun.prospring5.ch12.configuration;


import jun.prospring5.ch12.common.factory.HttpMessageConverterFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.oxm.castor.CastorMappingException;
import org.springframework.oxm.castor.CastorMarshaller;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;
import java.util.List;

@Configuration
@EnableWebMvc
// Add excludeFilters for ignoring all @Configuration classes from the component scan
// that avoid Spring MVC RequestMappingHandlerMapping happens twice when application starts.
@ComponentScan(basePackages = {"jun.prospring5.ch12"},
        excludeFilters = {@ComponentScan.Filter(
                type = FilterType.ANNOTATION, value = {Configuration.class})})
public class WebConfiguration implements WebMvcConfigurer {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private CastorMarshaller castorMarshaller;

    @Override
    public void configureDefaultServletHandling(
            DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void configureMessageConverters(
            List<HttpMessageConverter<?>> converters) {
        try {
            converters.add(HttpMessageConverterFactory
                    .newJsonHttpMessageConverter(
                            HttpMessageConverterFactory.newJsonObjectMapper()));
            converters.add(HttpMessageConverterFactory
                    .newXmlHttpMessageConverter(castorMarshaller));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Bean
    public CastorMarshaller castorMarshaller()
            throws CastorMappingException, IOException {
        return HttpMessageConverterFactory.newCastorMarshaller(
                context, "classpath:oxm-mapping.xml");
    }
}
