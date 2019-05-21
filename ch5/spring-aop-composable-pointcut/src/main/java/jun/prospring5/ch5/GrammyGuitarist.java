package jun.prospring5.ch5;

public class GrammyGuitarist implements Singer {

    @Override
    public void sing() {
        System.out.println("sing: Gravity is working against me\n" +
                "      And gravity wants to bring me down");
    }

    @Override
    public void sing(Guitar guitar) {
        System.out.println("play: " + guitar.play());
    }

    @Override
    public void rest() {
        System.out.println("zzz");
    }

    @Override
    public void talk() {
        System.out.println("talk");
    }
}
