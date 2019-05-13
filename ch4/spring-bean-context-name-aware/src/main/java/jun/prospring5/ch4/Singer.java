package jun.prospring5.ch4;

import org.springframework.beans.factory.BeanNameAware;

public class Singer implements BeanNameAware {

    private String name;

    @Override
    public void setBeanName(String name) {
        this.name = name;
    }

    public void singe() {
        System.out.println("Singer " + this.name + " - sing()");
    }
}
