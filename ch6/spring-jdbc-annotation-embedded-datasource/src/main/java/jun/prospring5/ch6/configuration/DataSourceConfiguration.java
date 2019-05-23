package jun.prospring5.ch6.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

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

    @Value("${databaseScript}")
    private Resource initScriptFile;

    @Bean
    public static PropertySourcesPlaceholderConfigurer
    propertySourcesPlaceholderConfigure() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Lazy
    @Bean(name = {"dataSource", "embeddedDataSource"})
    public DataSource dataSource() {

        SimpleDriverDataSource dataSource = null;

        try {
            dataSource = new SimpleDriverDataSource();
            @SuppressWarnings("unchecked")
            Class<? extends Driver> driverClass =
                    (Class<? extends Driver>)
                            Class.forName(this.driverClassName);
            dataSource.setDriverClass(driverClass);
            dataSource.setUrl(this.url);
            dataSource.setUsername(this.username);
            dataSource.setPassword(this.password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return dataSource;
    }

    @Bean
    public ResourceDatabasePopulator databasePopulator() {
        ResourceDatabasePopulator populator =
                new ResourceDatabasePopulator();

        populator.setScripts(initScriptFile);
        populator.setContinueOnError(false);

        return populator;
    }

    @Bean
    public DataSourceInitializer dataSourceInitializer(
            ResourceDatabasePopulator populator, DataSource dataSource) {
        DataSourceInitializer initializer
                = new DataSourceInitializer();

        initializer.setDatabasePopulator(populator);
        initializer.setDataSource(dataSource);

        return initializer;
    }
}
