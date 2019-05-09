package jun.prospring5.ch3;

public class TargetBean {

    private InjectBean injectedBean;

    public InjectBean getInjectedBean() {
        return injectedBean;
    }

    public void setInjectedBean(InjectBean injectedBean) {
        this.injectedBean = injectedBean;
    }

    @Override
    public String toString() {
        return "TargetBean{" +
                "injectedBean=" + injectedBean +
                '}';
    }
}
