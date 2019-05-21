package jun.prospring5.ch5;

public class GrammyGuitarist implements Singer {

    @Override
    public void sing() {
        System.out.println("sing: Wu...");
    }

    public void sing(Guitar guitar) {
        System.out.println("play: " + guitar.play());
    }

    public void rest() {
        System.out.println("zzz");
    }

    public void talk() {
        System.out.println("talk");
    }
}
