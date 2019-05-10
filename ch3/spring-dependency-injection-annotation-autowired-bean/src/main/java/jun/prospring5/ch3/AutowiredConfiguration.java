package jun.prospring5.ch3;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class AutowiredConfiguration {

    @Bean
    @Lazy
    public Target target() {
        return new Target();
    }

    @Bean("fooOne")
    public Foo fooOneImpl() {
        return new FooOneImpl();
    }

    @Bean("fooTwo")
    public Foo fooTwoImpl() {
        return new FooTwoImpl();
    }

    @Bean
    public Bar bar() {
        return new Bar();
    }
}
