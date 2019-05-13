package jun.prospring5.ch4;

import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;

public class Singer implements InitializingBean {

    private static final String DEFAULT_NAME = "Eric Clapton";
    private static final int INVALID_AGE = Integer.MAX_VALUE;

    private String name;
    private int age = INVALID_AGE;

    @PostConstruct
    private void doPostConstruct() {
        System.out.println("doPostConstruct:");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet:");
    }

    private void init() {

        System.out.println("Initializing bean:");

        if (this.name == null) {
            this.name = DEFAULT_NAME;
        }

        if (this.age == INVALID_AGE) {
            throw new IllegalArgumentException(
                    "You must set the age property of any beans of type " +
                    Singer.class);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Singer{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
