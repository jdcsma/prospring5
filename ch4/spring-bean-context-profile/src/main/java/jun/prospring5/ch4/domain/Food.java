package jun.prospring5.ch4.domain;

public final class Food {

    private String name;

    public Food() {
        super();
    }

    public Food(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Food{" +
                "name='" + name + '\'' +
                '}';
    }
}
