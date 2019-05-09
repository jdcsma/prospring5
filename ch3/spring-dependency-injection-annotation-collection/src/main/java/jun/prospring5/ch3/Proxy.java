package jun.prospring5.ch3;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("proxy")
public class Proxy {

    @Value("This is a bean instance")
    private String value;

    @Override
    public String toString() {
        return value;
    }
}
