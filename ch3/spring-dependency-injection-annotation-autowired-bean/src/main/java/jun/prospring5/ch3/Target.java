package jun.prospring5.ch3;

import org.springframework.beans.factory.annotation.Autowired;

public class Target {

    private Foo fooOne;
    private Foo fooTwo;
    private Bar bar;

    public Foo getFooOne() {
        return fooOne;
    }

    @Autowired
    public void setFooOne(Foo fooOne) {
        this.fooOne = fooOne;
        System.out.println("Property fooOne set");
    }

    public Foo getFooTwo() {
        return fooTwo;
    }

    @Autowired
    public void setFooTwo(Foo fooTwo) {
        this.fooTwo = fooTwo;
        System.out.println("Property fooTwo set");
    }

    public Bar getBar() {
        return bar;
    }

    @Autowired
    public void setBar(Bar bar) {
        this.bar = bar;
        System.out.println("Property bar set");
    }
}
