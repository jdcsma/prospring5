package jun.prospring5.ch4;

import org.springframework.stereotype.Component;

@Component
public class HelloWorld {

    public void sayHi() {
        System.out.println("Hello World!");
    }
}
