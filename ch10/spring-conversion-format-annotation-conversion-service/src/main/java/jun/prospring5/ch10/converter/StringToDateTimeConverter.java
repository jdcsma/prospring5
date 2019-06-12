package jun.prospring5.ch10.converter;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.core.convert.converter.Converter;

import javax.annotation.PostConstruct;

public class StringToDateTimeConverter
        implements Converter<String, DateTime> {

    private static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";
    private DateTimeFormatter dateTimeFormatter;
    private String datePattern = DEFAULT_DATE_PATTERN;

    public void setDatePattern(String datePattern) {
        this.datePattern = datePattern;
    }

    @PostConstruct
    public void init() {
        dateTimeFormatter = DateTimeFormat.forPattern(datePattern);
    }

    @Override
    public DateTime convert(String source) {
        return dateTimeFormatter.parseDateTime(source);
    }
}
