package jun.prospring5.ch7.configuration;

import com.alibaba.druid.pool.DruidDataSource;
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
import java.sql.SQLException;
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

    @Value("${druid.filters}")
    String druidFilters;

    @Value("${druid.maxActive}")
    Integer druidMaxActive;

    @Value("${druid.initialSize}")
    Integer druidInitialSize;

    @Value("${druid.maxWait}")
    Integer druidMaxWait;

    @Value("${druid.minIdle}")
    Integer druidMinIdle;

    @Value("${druid.timeBetweenEvictionRunsMillis}")
    Integer druidTimeBetweenEvictionRunsMillis;

    @Value("${druid.minEvictableIdleTimeMillis}")
    Integer druidMinEvictableIdleTimeMillis;

    @Value("${druid.validationQuery}")
    String druidValidationQuery;

    @Value("${druid.testWhileIdle}")
    boolean druidTestWhileIdle;

    @Value("${druid.testOnBorrow}")
    boolean druidTestOnBorrow;

    @Value("${druid.testOnReturn}")
    boolean druidTestOnReturn;

    @Value("${druid.poolPreparedStatements}")
    boolean druidPoolPreparedStatements;

    @Value("${druid.maxPoolPreparedStatementPerConnectionSize}")
    Integer druidMaxPoolPreparedStatementPerConnectionSize;

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

    @Bean
    public static PropertySourcesPlaceholderConfigurer
    propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean(initMethod = "init", destroyMethod = "close")
    public DataSource dataSource() {

        DruidDataSource dataSource = new DruidDataSource();

        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        try {
            dataSource.addFilters(druidFilters);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        dataSource.setMaxActive(druidMaxActive);
        dataSource.setInitialSize(druidInitialSize);
        dataSource.setMaxWait(druidMaxWait);
        dataSource.setMinIdle(druidMinIdle);
        dataSource.setTimeBetweenEvictionRunsMillis(druidTimeBetweenEvictionRunsMillis);
        dataSource.setMinEvictableIdleTimeMillis(druidMinEvictableIdleTimeMillis);
        dataSource.setValidationQuery(druidValidationQuery);
        dataSource.setTestWhileIdle(druidTestWhileIdle);
        dataSource.setTestOnBorrow(druidTestOnBorrow);
        dataSource.setTestOnReturn(druidTestOnReturn);
        dataSource.setPoolPreparedStatements(druidPoolPreparedStatements);
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(druidMaxPoolPreparedStatementPerConnectionSize);

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
        properties.put(Environment.HBM2DDL_AUTO, "create-drop");
        properties.put(Environment.SHOW_SQL, hibernateShowSql);
        properties.put(Environment.FORMAT_SQL, hibernateFormatSql);
        properties.put(Environment.USE_SQL_COMMENTS, hibernateUseSqlComments);
        properties.put(Environment.MAX_FETCH_DEPTH, hibernateMaxFetchDepth);

        return properties;
    }
}
