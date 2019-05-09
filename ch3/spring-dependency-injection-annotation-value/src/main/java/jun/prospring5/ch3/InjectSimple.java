package jun.prospring5.ch3;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("injectSimple")
public class InjectSimple {

    @Value("Jonh Mayer")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "InjectSimple{" +
                "name='" + name + '\'' +
                '}';
    }
}
