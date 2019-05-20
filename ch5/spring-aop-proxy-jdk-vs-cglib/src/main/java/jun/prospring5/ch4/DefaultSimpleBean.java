package jun.prospring5.ch4;

public class DefaultSimpleBean implements SimpleBean {

    private long dummy = 0;

    @Override
    public void advised() {
        this.dummy = System.currentTimeMillis();
    }

    @Override
    public void unadvised() {
        this.dummy = System.currentTimeMillis();
    }
}
