package jun.prospring5.ch3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("standardLookupBean")
public class StandardLookupDemoBean implements DemoBean {

    private SomeObject someObject;

    public SomeObject getSomeObject() {
        return someObject;
    }

    @Autowired
    @Qualifier("someObject")
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
