package jun.prospring5.ch9.configuration;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import jun.prospring5.ch9.transaction.AtomikosJtaPlatform;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Environment;
import org.hibernate.engine.transaction.jta.platform.spi.JtaPlatform;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@Configuration
@EnableJpaRepositories
@PropertySource("classpath:jdbc.properties")
public class JtaConfiguration {

    @Value("${databaseDriverClassName}")
    private String driverClassName;

    @Value("${databaseUrlA}")
    private String urlA;

    @Value("${databaseUrlB}")
    private String urlB;

    @Value("${databaseUsername}")
    private String username;

    @Value("${databasePassword}")
    private String password;

    @Value("${hibernate.dialect}")
    private String hibernateDialect;

    @Value("${hibernate.format_sql}")
    private boolean hibernateFormatSql;

    @Value("${hibernate.use_sql_comments}")
    private boolean hibernateUseSqlComments;

    @Value("${hibernate.show_sql}")
    private boolean hibernateShowSql;

    @Value("${hibernate.max_fetch_depth}")
    private Integer hibernateMaxFetchDepth;

    @Value("${hibernate.hbm2ddl.auto}")
    private String hibernateHbm2ddlAuto;

    @Bean
    public static PropertySourcesPlaceholderConfigurer
    propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean(initMethod = "init", destroyMethod = "close")
    public DataSource dataSourceA() {
        return newDataSource("XADBMSA", newPropertiesXA(urlA));
    }

    @Bean(initMethod = "init", destroyMethod = "close")
    public DataSource dataSourceB() {
        return newDataSource("XADBMSB", newPropertiesXA(urlB));
    }

    @Bean
    @DependsOn("atomikosJtaPlatform")
    public EntityManagerFactory entityManagerFactoryA() {
        return newEntityManagerFactory(dataSourceA(),
                "entityManagerFactoryB");
    }

    @Bean
    @DependsOn("atomikosJtaPlatform")
    public EntityManagerFactory entityManagerFactoryB() {
        return newEntityManagerFactory(dataSourceB(),
                "entityManagerFactoryA");
    }

    private DataSource newDataSource(String resourceName, Properties xaProperties) {
        AtomikosDataSourceBean dataSource =
                new AtomikosDataSourceBean();
        dataSource.setUniqueResourceName(resourceName);
        dataSource.setXaDataSourceClassName(driverClassName);
        dataSource.setXaProperties(xaProperties);
        dataSource.setPoolSize(3);
        return dataSource;
    }

    private Properties newPropertiesXA(String url) {
        Properties properties = new Properties();
        properties.put("URL", url);
        properties.put("user", username);
        properties.put("password", password);
        return properties;
    }

    private Properties hibernateProperties() {

        Properties properties = new Properties();

        properties.put(Environment.JTA_PLATFORM,
                "jun.prospring5.ch9.transaction.AtomikosJtaPlatform");
        properties.put(Environment.TRANSACTION_COORDINATOR_STRATEGY, "jta");

        properties.put(Environment.DIALECT, hibernateDialect);
        properties.put(Environment.SHOW_SQL, hibernateShowSql);
        properties.put(Environment.FORMAT_SQL, hibernateFormatSql);
        properties.put(Environment.USE_SQL_COMMENTS, hibernateUseSqlComments);
        properties.put(Environment.MAX_FETCH_DEPTH, hibernateMaxFetchDepth);
//        properties.put(Environment.HBM2DDL_AUTO, hibernateHbm2ddlAuto);

        return properties;
    }

    private EntityManagerFactory newEntityManagerFactory(
            DataSource dataSource, String persistenceUnitName) {
        LocalContainerEntityManagerFactoryBean factoryBean
                = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setPackagesToScan("jun.prospring5.ch9.entity");
        factoryBean.setJtaDataSource(dataSource);
        factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        factoryBean.setJpaProperties(hibernateProperties());
        factoryBean.setPersistenceUnitName(persistenceUnitName);
        factoryBean.afterPropertiesSet();
        EntityManagerFactory factory = factoryBean.getObject();
        return factory;
    }
}
