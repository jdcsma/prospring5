package jun.prospring5.ch9.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;

import javax.persistence.EntityManagerFactory;

@Configuration
@ComponentScan(basePackages = {"jun.prospring5.ch9"})
public class ServiceConfiguration {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Bean
    public TransactionTemplate txTemplateDefault() {
        return new TransactionTemplateBuilder()
                .setTransactionManager(transactionManager())
                .build();
    }

    @Bean
    public TransactionTemplate txTemplatePropagationNever() {
        return new TransactionTemplateBuilder()
                .setPropagation(TransactionDefinition.PROPAGATION_NEVER)
                .setTimeout(30)
                .setTransactionManager(transactionManager())
                .build();
    }

    @Bean
    public TransactionTemplate txTemplateReadOnly() {
        return new TransactionTemplateBuilder()
                .setReadOnly(true)
                .setTransactionManager(transactionManager())
                .build();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager(entityManagerFactory);
    }

    private static class TransactionTemplateBuilder {

        private int propagation = TransactionDefinition.PROPAGATION_REQUIRED;
        private int timeout = TransactionDefinition.TIMEOUT_DEFAULT;
        private boolean readOnly = false;
        private PlatformTransactionManager transactionManager;

        public TransactionTemplateBuilder setPropagation(int propagation) {
            this.propagation = propagation;
            return this;
        }

        public TransactionTemplateBuilder setTimeout(int timeout) {
            this.timeout = timeout;
            return this;
        }

        public TransactionTemplateBuilder setReadOnly(boolean readOnly) {
            this.readOnly = readOnly;
            return this;
        }

        public TransactionTemplateBuilder setTransactionManager(
                PlatformTransactionManager transactionManager) {
            this.transactionManager = transactionManager;
            return this;
        }

        public TransactionTemplate build() {
            TransactionTemplate template = new TransactionTemplate();
            template.setPropagationBehavior(propagation);
            template.setReadOnly(readOnly);
            template.setTimeout(timeout);
            template.setTransactionManager(transactionManager);
            return template;
        }
    }
}
