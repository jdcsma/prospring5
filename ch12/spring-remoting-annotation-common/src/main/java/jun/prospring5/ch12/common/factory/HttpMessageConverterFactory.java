package jun.prospring5.ch12.common.factory;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.oxm.castor.CastorMappingException;
import org.springframework.oxm.castor.CastorMarshaller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public final class HttpMessageConverterFactory {

    public static MappingJackson2HttpMessageConverter
    newJsonHttpMessageConverter(ObjectMapper objectMapper) {
        MappingJackson2HttpMessageConverter converter =
                new MappingJackson2HttpMessageConverter(
                        objectMapper);
        return converter;
    }

    public static ObjectMapper newJsonObjectMapper() {
        return Jackson2ObjectMapperBuilder.json()
                .indentOutput(true)
                .serializationInclusion(JsonInclude.Include.NON_NULL)
                .dateFormat(new SimpleDateFormat("yyyy-MM-dd"))
                .build();
    }

    public static MarshallingHttpMessageConverter
    newXmlHttpMessageConverter(CastorMarshaller castorMarshaller)
            throws CastorMappingException, IOException {
        MarshallingHttpMessageConverter converter =
                new MarshallingHttpMessageConverter();
        converter.setMarshaller(castorMarshaller);
        converter.setUnmarshaller(castorMarshaller);
        List<MediaType> mediaTypes = new ArrayList<>();
        mediaTypes.add(new MediaType("application", "xml"));
        converter.setSupportedMediaTypes(mediaTypes);
        return converter;
    }

    public static CastorMarshaller
    newCastorMarshaller(ApplicationContext context,
                        String resourceLocation)
            throws CastorMappingException, IOException {
        CastorMarshaller marshaller = new CastorMarshaller();
        marshaller.setMappingLocation(
                context.getResource(resourceLocation));
        return marshaller;
    }
}
