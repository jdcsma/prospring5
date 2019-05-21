package jun.prospring5.ch5;

public class Documentarist {

    private GrammyGuitarist guitarist;

    public void execute() {
        guitarist.sing();
        guitarist.sing(new TaylorGuitar());
        guitarist.sing(new MartinGuitar());
        guitarist.talk();
    }

    public void setGuitarist(GrammyGuitarist guitarist) {
        this.guitarist = guitarist;
    }
}
