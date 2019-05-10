package jun.prospring5.ch3;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

@Component("johnMayer")
@DependsOn("gopher")
public class Singer implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(
            ApplicationContext applicationContext)
            throws BeansException {
        this.applicationContext = applicationContext;
    }

    private Guitar guitar;

    public void sing() {
        guitar = applicationContext.getBean("gopher", Guitar.class);
        guitar.sing();
    }
}
