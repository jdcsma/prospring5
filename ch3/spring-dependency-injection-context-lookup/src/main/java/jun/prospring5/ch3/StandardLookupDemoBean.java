package jun.prospring5.ch3;

public class StandardLookupDemoBean implements DemoBean {

    private SomeObject someObject;

    public SomeObject getSomeObject() {
        return someObject;
    }

    public void setSomeObject(SomeObject someObject) {
        this.someObject = someObject;
    }

    @Override
    public SomeObject getObject() {
        return someObject;
    }

    @Override
    public void doSomething() {
        someObject.doSomething();
    }
}
