package jun.prospring5.ch3;

public class TargetBean2 {

    private InjectBean injectedBean;

    public InjectBean getInjectedBean() {
        return injectedBean;
    }

    public void setInjectedBean(InjectBean injectedBean) {
        this.injectedBean = injectedBean;
    }

    @Override
    public String toString() {
        return "TargetBean2{" +
                "injectedBean=" + injectedBean +
                '}';
    }
}
