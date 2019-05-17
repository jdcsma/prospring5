package jun.prospring5.ch4;

public class GoodSinger implements Singer {

    @Override
    public void sing() {
        System.out.println(">> good singer: sing:");
        System.out.println("  Who can says I can't be free\n" +
                "  From all of the things that I used to be");
    }

    @Override
    public void dance() {
        System.out.println(">> good singer: dance:");
        System.out.println("  jump! jump!! jump!!!");
    }
}
