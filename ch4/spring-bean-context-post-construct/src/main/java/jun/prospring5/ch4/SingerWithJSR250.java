package jun.prospring5.ch4;

import javax.annotation.PostConstruct;

public class SingerWithJSR250 {

    private static final String DEFAULT_NAME = "Eric Clapton";
    private static final int INVALID_AGE = Integer.MAX_VALUE;

    private String name;
    private int age = INVALID_AGE;

    @PostConstruct
    private void init() throws Exception {
        System.out.println("Initializing bean:");

        if (this.name == null) {
            this.name = DEFAULT_NAME;
        }

        if (this.age == INVALID_AGE) {
            throw new IllegalArgumentException(
                    "You must set the age property of any beans of type " +
                    SingerWithJSR250.class);
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
        return "SingerWithJSR250{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
