package jun.prospring5.ch3;

public abstract class AbstractLookupDemoBean implements DemoBean {

    public abstract SomeObject getObject();

    @Override
    public void doSomething() {
        getObject().doSomething();
    }
}
