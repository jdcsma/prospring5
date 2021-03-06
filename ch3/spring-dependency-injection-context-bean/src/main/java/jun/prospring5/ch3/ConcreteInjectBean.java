package jun.prospring5.ch3;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConcreteInjectBean implements InjectBean {

    @Value("This is an injected bean by interface.")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ConcreteInjectBean{" +
                "name='" + name + '\'' +
                '}';
    }
}
