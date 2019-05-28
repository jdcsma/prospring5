package jun.prospring5.ch7.configuration;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = {"jun.prospring5.ch7"})
@EnableTransactionManagement
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

    @Value("${hibernate.dialect}")
    private String hibernateDialect;

    @Value("${hibernate.format_sql}")
    private boolean hibernateFormatSql;

    @Value("${hibernate.use_sql_comments}")
    private boolean hibernateUseSqlComments;

    @Value("${hibernate.show_sql}")
    private boolean hibernateShowSql;

    @Value("${hibernate.jdbc.max_fetch_depth}")
    private Integer hibernateJdbcMaxFetchDepth;

    @Value("${hibernate.jdbc.batch_size}")
    private Integer hibernateJdbcBatchSize;

    @Value("${hibernate.jdbc.fetch_size}")
    private Integer hibernateJdbcFetchSize;

    @Bean
    public DataSource dataSource() {

        DriverManagerDataSource dataSource =
                new DriverManagerDataSource();

        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        return dataSource;
    }

    @Bean
    public ResourceDatabasePopulator resourceDatabasePopulator() {

        ResourceDatabasePopulator databasePopulator =
                new ResourceDatabasePopulator();

        databasePopulator.setScripts(initScriptFile);
        databasePopulator.setContinueOnError(false);

        return databasePopulator;
    }

    @Bean
    public DataSourceInitializer dataSourceInitializer(
            ResourceDatabasePopulator databasePopulator,
            DataSource dataSource) {

        DataSourceInitializer dataSourceInitializer =
                new DataSourceInitializer();

        dataSourceInitializer.setDataSource(dataSource);
        dataSourceInitializer.setDatabasePopulator(databasePopulator);

        return dataSourceInitializer;
    }

    @Bean
    public Properties hibernateProperties() {

        Properties properties = new Properties();

        properties.put("hibernate.connection.driver_class", driverClassName);
        properties.put("hibernate.connection.url", url);
        properties.put("hibernate.connection.username", username);
        properties.put("hibernate.connection.password", password);
        properties.put("hibernate.dialect", hibernateDialect);
        properties.put("hibernate.format_sql", hibernateFormatSql);
        properties.put("hibernate.use_sql_comments", hibernateUseSqlComments);
        properties.put("hibernate.show_sql", hibernateShowSql);
        properties.put("hibernate.max_fetch_depth", hibernateJdbcMaxFetchDepth);
        properties.put("hibernate.jdbc.batch_size", hibernateJdbcBatchSize);
        properties.put("hibernate.jdbc.fetch_size", hibernateJdbcFetchSize);

        return properties;
    }

    @Bean
    @Qualifier("hibernateProperties")
    public FactoryBean<SessionFactory> sessionFactory(
            DataSource dataSource, Properties hibernateProperties) {
        LocalSessionFactoryBean sessionFactoryBean =
                new LocalSessionFactoryBean();

        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setPackagesToScan("jun.prospring5.ch7.entity");
        sessionFactoryBean.setHibernateProperties(hibernateProperties);

        return sessionFactoryBean;
    }

    @Bean
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory);
        return transactionManager;
    }
}
