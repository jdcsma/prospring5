package jun.prospring5.ch4;

import com.sun.javafx.runtime.SystemProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class DestructiveBeanConfiguration {

    @Lazy
    @Bean(initMethod = "init", destroyMethod = "destroy")
    public DestructiveBean destructiveBean() {
        DestructiveBean bean = new DestructiveBean();
        bean.setFilePath(SystemProperties.getProperty("java.io.tmpdir") +
                SystemProperties.getProperty("file.separator") +
                "test.txt");
        return bean;
    }
}
