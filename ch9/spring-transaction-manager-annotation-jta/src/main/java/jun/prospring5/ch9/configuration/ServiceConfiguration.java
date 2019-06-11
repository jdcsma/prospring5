package jun.prospring5.ch9.configuration;

import com.atomikos.icatch.config.UserTransactionService;
import com.atomikos.icatch.config.UserTransactionServiceImp;
import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import jun.prospring5.ch9.transaction.AtomikosJtaPlatform;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = {"jun.prospring5.ch9"})
public class ServiceConfiguration {

    private static final Logger logger =
            LoggerFactory.getLogger(ServiceConfiguration.class);

    @Bean(initMethod = "init", destroyMethod = "shutdownForce")
    public UserTransactionService userTransactionService() {
        Properties properties = new Properties();
        properties.put("com.atomikos.icatch.service",
                "com.atomikos.icatch.standalone.UserTransactionServiceFactory");
        return new UserTransactionServiceImp(properties);
    }

    @Bean(initMethod = "init", destroyMethod = "close")
    @DependsOn("userTransactionService")
    public UserTransactionManager userTransactionManager() {
        UserTransactionManager manager = new UserTransactionManager();
        manager.setStartupTransactionService(false);
        manager.setForceShutdown(true);
        return manager;
    }

    @Bean
    @DependsOn("userTransactionService")
    public UserTransaction userTransaction() {
        UserTransactionImp imp = new UserTransactionImp();
        try {
            imp.setTransactionTimeout(300);
        } catch (SystemException e) {
            logger.error("Configuration exception:", e);
            return null;
        }
        return imp;
    }

    @Bean
    public AtomikosJtaPlatform atomikosJtaPlatform() {
        AtomikosJtaPlatform jtaPlatform = new AtomikosJtaPlatform();
        AtomikosJtaPlatform.setUserTx(userTransaction());
        AtomikosJtaPlatform.setTxMgr(userTransactionManager());
        return jtaPlatform;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JtaTransactionManager manager = new JtaTransactionManager();
        manager.setTransactionManager(userTransactionManager());
        manager.setUserTransaction(userTransaction());
        return manager;
    }
}
