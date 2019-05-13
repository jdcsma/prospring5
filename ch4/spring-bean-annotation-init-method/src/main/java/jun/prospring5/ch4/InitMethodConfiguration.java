package jun.prospring5.ch4;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class InitMethodConfiguration {

    @Lazy
    @Bean(initMethod = "init")
    public Singer singerOne() {
        Singer singer = new Singer();
        singer.setName("John Mayer");
        singer.setAge(39);
        return singer;
    }

    @Lazy
    @Bean(initMethod = "init")
    public Singer singerTwo() {
        Singer singer = new Singer();
        singer.setAge(72);
        return singer;
    }

    @Lazy
    @Bean(initMethod = "init")
    public Singer singerThree() {
        Singer singer = new Singer();
        singer.setName("John Butler");
        return singer;
    }
}
