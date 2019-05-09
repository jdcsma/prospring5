package jun.prospring5.ch3;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

@Component("abstractLookupBean")
public abstract class AbstractLookupDemoBean implements DemoBean {

    @Lookup("someObject")
//    public SomeObject getObject() {
//        return null;
//    }
    public abstract SomeObject getObject();

    @Override
    public void doSomething() {
        getObject().doSomething();
    }
}
