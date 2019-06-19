package jun.prospring5.ch12.common.domain.handler;

import org.apache.commons.lang3.StringUtils;
import org.exolab.castor.mapping.GeneralizedFieldHandler;
import org.exolab.castor.mapping.ValidityException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Properties;

public class DateTimeFieldHandler extends GeneralizedFieldHandler {

    private static final Logger logger =
            LoggerFactory.getLogger(DateTimeFormatter.class);

    private static String dateFormatPattern;

    @Override
    public void setConfiguration(Properties config)
            throws ValidityException {
        dateFormatPattern = config.getProperty("date-format");
        logger.info("DateTimeFieldHandler.setConfiguration:" + dateFormatPattern);
    }

    @Override
    public Object convertUponGet(Object value) {
        Date dateTime = (Date) value;
        return format(dateTime);
    }

    @Override
    public Object convertUponSet(Object value) {
        String dateTimeString = (String) value;
        return parse(dateTimeString);
    }

    @Override
    public Class<Date> getFieldType() {
        return Date.class;
    }

    private static Date parse(final String dateTimeString) {
        Date dateTime = null;
        if (StringUtils.isNotEmpty(dateTimeString)) {
            SimpleDateFormat parser =
                    new SimpleDateFormat(dateFormatPattern);
            try {
                dateTime = parser.parse(dateTimeString);
            } catch (ParseException e) {
                logger.error("Not a valid date:" + dateTimeString, e);
            }
        }
        if (dateTime == null) {
            dateTime = new Date();
        }
        logger.info("DateTimeFieldHandler.parse:" + dateTime);
        return dateTime;
    }

    private static String format(final Date dateTime) {
        String dateTimeString = "";
        if (dateTime != null) {
            SimpleDateFormat formatter =
                    new SimpleDateFormat(dateFormatPattern);
            dateTimeString = formatter.format(dateTime);
        }
        logger.info("DateTimeFieldHandler.format:" + dateTimeString);
        return dateTimeString;
    }
}
