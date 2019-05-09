package jun.prospring5.ch3;

public class InjectSimpleConfig {

    private String name = "John Mayer";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "InjectSimpleConfig{" +
                "name='" + name + '\'' +
                '}';
    }
}
