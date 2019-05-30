package jun.prospring5.ch7.configuration;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
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

    @Value("${dataSource.dbcp2.initial_size}")
    private Integer dbcp2InitialSize;

    @Value("${dataSource.dbcp2.min_idle_size}")
    private Integer dbcp2MinIdleSize;

    @Value("${dataSource.dbcp2.max_idle_size}")
    private Integer dbcp2MaxIdleSize;

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

    @Bean(destroyMethod = "close")
    public DataSource dataSource() {

        BasicDataSource dataSource = new BasicDataSource();

        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        dataSource.setInitialSize(dbcp2InitialSize);
        dataSource.setMinIdle(dbcp2MinIdleSize);
        dataSource.setMaxIdle(dbcp2MaxIdleSize);

        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
        LocalSessionFactoryBean sessionFactoryBean =
                new LocalSessionFactoryBean();
        sessionFactoryBean.setPackagesToScan("jun.prospring5.ch7.entity");
        sessionFactoryBean.setHibernateProperties(
                hibernateProperties(dataSource));
        return sessionFactoryBean;
    }

    @Bean
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);
    }

    private Properties hibernateProperties(DataSource dataSource) {

        Properties properties = new Properties();

        properties.put(Environment.DATASOURCE, dataSource);
        properties.put(Environment.DIALECT, hibernateDialect);
        properties.put(Environment.SHOW_SQL, hibernateShowSql);
        properties.put(Environment.FORMAT_SQL, hibernateFormatSql);
        properties.put(Environment.USE_SQL_COMMENTS, hibernateUseSqlComments);
        properties.put(Environment.MAX_FETCH_DEPTH, hibernateMaxFetchDepth);
        properties.put(Environment.HBM2DDL_AUTO, hibernateHbm2ddlAuto);

        return properties;
    }
}
