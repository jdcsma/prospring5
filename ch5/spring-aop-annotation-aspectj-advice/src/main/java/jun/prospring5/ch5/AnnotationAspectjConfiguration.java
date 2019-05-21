package jun.prospring5.ch5;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
// or use instead of <context:component-scan base-package="jun.prospring5.ch5"/> in app-context.xml
@ComponentScan(basePackages = {"jun.prospring5.ch5"})
// or use instead of <aop:aspectj-autoproxy proxy-target-class="true"> in app-context.xml
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AnnotationAspectjConfiguration {

    @Bean("johnMayer")
    public GrammyGuitarist grammyGuitarist() {
        return new GrammyGuitarist();
    }
}
