package jun.prospring5.ch4;

public class GoodGuitarist implements Singer {

    @Override
    public void sing() {
        System.out.println("Who says I can't be free\n" +
                "From all of the things that I used to be");
    }
}
