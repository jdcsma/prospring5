package jun.prospring5.ch3;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("someObject")
@Scope("prototype")
public class SomeObject {

    public void doSomething() {
        // Commented because it pollutes the output.
        // System.out.println("Say: Hi.");
    }
}
