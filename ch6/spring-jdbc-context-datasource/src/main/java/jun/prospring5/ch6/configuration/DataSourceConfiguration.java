package jun.prospring5.ch6.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.sql.Driver;

@Configuration
@PropertySource("classpath:jdbc.properties")
public class DataSourceConfiguration {

    @Value("${databaseDriverClassName}")
    private String driverClassName;

    @Value("${databaseUrl}")
    private String url;

    @Value("${databaseUsername}")
    private String username;

    @Value("${databasePassword}")
    private String password;

    @Bean
    public static PropertySourcesPlaceholderConfigurer
    propertySourcesPlaceholderConfigure() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Lazy
    @Bean
    public DataSource dataSource() {

        SimpleDriverDataSource dataSource = null;

        try {
            dataSource = new SimpleDriverDataSource();
            Class<? extends Driver> driverClass =
                    (Class<? extends Driver>) Class.forName(this.driverClassName);
            dataSource.setDriverClass(driverClass);
            dataSource.setUrl(this.url);
            dataSource.setUsername(this.username);
            dataSource.setPassword(this.password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return dataSource;
    }
}
