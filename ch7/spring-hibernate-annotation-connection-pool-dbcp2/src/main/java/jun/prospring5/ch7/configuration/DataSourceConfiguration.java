package jun.prospring5.ch7.configuration;

import jun.prospring5.ch7.entity.Album;
import jun.prospring5.ch7.entity.Instrument;
import jun.prospring5.ch7.entity.Singer;
import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.Resource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.PreDestroy;
import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@ComponentScan(basePackages = {"jun.prospring5.ch7"})
@EnableTransactionManagement
@PropertySource("classpath:jdbc.properties")
public class DataSourceConfiguration {

    private static StandardServiceRegistry registry = null;
    private static SessionFactory sessionFactory = null;

    @Value("${databaseDriverClassName}")
    private String driverClassName;

    @Value("${databaseUrl}")
    private String url;

    @Value("${databaseUsername}")
    private String username;

    @Value("${databasePassword}")
    private String password;

    @Value("${basicDataSource.pool.initial_size}")
    private Integer dataSourcePoolInitialSize;

    @Value("${basicDataSource.pool.min_idle_size}")
    private Integer dataSourcePoolMinIdleSize;

    @Value("${basicDataSource.pool.max_idle_size}")
    private Integer dataSourcePoolMaxIdleSize;

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

    @PreDestroy
    void destroy() {
        if (registry != null) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

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

        dataSource.setInitialSize(dataSourcePoolInitialSize);
        dataSource.setMinIdle(dataSourcePoolMinIdleSize);
        dataSource.setMaxIdle(dataSourcePoolMaxIdleSize);

        return dataSource;
    }

    @Bean
    public SessionFactory sessionFactory(DataSource dataSource) {

        try {
            StandardServiceRegistryBuilder registryBuilder =
                    new StandardServiceRegistryBuilder();
            registryBuilder.applySettings(hibernateSettings(dataSource));

            registry = registryBuilder.build();

            MetadataSources sources = new MetadataSources(registry);
            List<Class<?>> classes = Arrays.asList(
                    Singer.class,
                    Album.class,
                    Instrument.class);
            classes.forEach(c -> sources.addAnnotatedClass(c));

            Metadata metadata = sources.getMetadataBuilder().build();
            sessionFactory = metadata.getSessionFactoryBuilder().build();

        } catch (Exception e) {
            if (registry != null) {
                StandardServiceRegistryBuilder.destroy(registry);
            }
            throw e;
        }

        return sessionFactory;
    }

    @Bean
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);
    }

    private Map<String, Object> hibernateSettings(DataSource dataSource) {
        Map<String, Object> settings = new HashMap<>();

        settings.put(Environment.DATASOURCE, dataSource);
        settings.put(Environment.HBM2DDL_AUTO, "create-drop");
        settings.put(Environment.SHOW_SQL, hibernateShowSql);
        settings.put(Environment.FORMAT_SQL, hibernateFormatSql);
        settings.put(Environment.USE_SQL_COMMENTS, hibernateUseSqlComments);
        settings.put(Environment.MAX_FETCH_DEPTH, hibernateMaxFetchDepth);

        return settings;
    }
}
