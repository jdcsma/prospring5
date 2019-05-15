package jun.prospring5.ch4;

import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.PropertiesEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

public class PropertyEditorBean {

    private byte[] bytes;               // ByteArrayPropertyEditor
    private Character character;        // CharacterEditor
    private Class claxx;                // ClassEditor
    private Boolean trueOrFalse;        // CustomBooleanEditor
    private List<String> stringList;    // CustomCollectionEditor
    private Date date;                  // CustomDateEditor
    private Float floatValue;           // CustomNumberEditor
    private File file;                  // FileEditor
    private InputStream stream;         // InputStreamEditor
    private Locale locale;              // LocaleEditor
    private Pattern pattern;            // PatternEditor
    private Properties properties;      // PropertiesEditor
    private String trimString;          // StringTrimmerEditor
    private URL url;                    // URLEditor

    public void setBytes(byte[] bytes) {
        System.out.println("Setting bytes: " + Arrays.toString(bytes));
        this.bytes = bytes;
    }

    public void setCharacter(Character character) {
        System.out.println("Setting character: " + character);
        this.character = character;
    }

    public void setClaxx(Class claxx) {
        System.out.println("Setting class: " + claxx.getName());
        this.claxx = claxx;
    }

    public void setTrueOrFalse(Boolean trueOrFalse) {
        System.out.println("Setting boolean: " + trueOrFalse);
        this.trueOrFalse = trueOrFalse;
    }

    public void setStringList(List<String> stringList) {
        System.out.println("Setting stringList: " + stringList);
        this.stringList = stringList;
    }

    public void setDate(Date date) {
        System.out.println("Setting date: " + date);
        this.date = date;
    }

    public void setFloatValue(Float floatValue) {
        System.out.println("Setting floatValue: " + floatValue);
        this.floatValue = floatValue;
    }

    public void setFile(File file) {
        System.out.println("Setting file: " + file.getName());
        this.file = file;
    }

    public void setStream(InputStream stream) {
        System.out.println("Setting input stream: " + stream);
        this.stream = stream;
    }

    public void setLocale(Locale locale) {
        System.out.println("Setting locale: " + locale.getDisplayName());
        this.locale = locale;
    }

    public void setPattern(Pattern pattern) {
        System.out.println("Setting pattern: " + pattern);
        this.pattern = pattern;
    }

    public void setProperties(Properties properties) {
        System.out.println("Setting properties: " + properties);
        this.properties = properties;
    }

    public void setTrimString(String trimString) {
        System.out.println("Setting trim string: " + trimString);
        this.trimString = trimString;
    }

    public void setUrl(URL url) {
        System.out.println("Setting url: " + url);
        this.url = url;
    }

    public static class CustomPropertyEditorRegistrar
            implements PropertyEditorRegistrar {
        @Override
        public void registerCustomEditors(
                PropertyEditorRegistry registry) {

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            registry.registerCustomEditor(Date.class,
                    new CustomDateEditor(formatter, true));

            registry.registerCustomEditor(String.class,
                    new StringTrimmerEditor(true));

            registry.registerCustomEditor(Properties.class,
                    new PropertiesEditor() {
                        @Override
                        public void setAsText(String text) throws IllegalArgumentException {
                            Properties props = new Properties();
                            if (text != null) {
                                try {

                                    String[] kv = text.split(" ");

                                    for (String s : kv) {
                                        // Must use the ISO-8859-1 encoding because Properties.load(stream) expects it.
                                        ByteArrayInputStream is = new ByteArrayInputStream(
                                                s.getBytes(StandardCharsets.ISO_8859_1));
                                        props.load(is);
                                    }
                                }
                                catch (IOException ex) {
                                    // Should never happen.
                                    throw new IllegalArgumentException(
                                            "Failed to parse [" + text + "] into Properties", ex);
                                }
                            }
                            setValue(props);
                        }
                    });
        }
    }
}
