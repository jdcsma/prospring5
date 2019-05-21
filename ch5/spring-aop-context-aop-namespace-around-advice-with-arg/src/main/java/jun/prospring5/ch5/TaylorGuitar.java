package jun.prospring5.ch5;

public class TaylorGuitar implements Guitar {
    @Override
    public String name() {
        return "Taylor";
    }

    @Override
    public String play() {
        return this.name() + ": Em Em9 D Em G D Em9 Em";
    }
}
