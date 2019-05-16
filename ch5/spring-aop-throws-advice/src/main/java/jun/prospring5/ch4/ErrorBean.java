package jun.prospring5.ch4;

public class ErrorBean {

    public void errorMethod1() throws Exception {
        throw new Exception("Generic Exception");
    }

    public void errorMethod2() throws IllegalArgumentException {
        throw new IllegalArgumentException("IllegalArgument Exception");
    }
}
