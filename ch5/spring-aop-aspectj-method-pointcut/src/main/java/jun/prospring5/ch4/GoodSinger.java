package jun.prospring5.ch4;

public class GoodSinger implements Singer {

    @Override
    public void sing() {
        System.out.println(">> good singer: sing:");
        this.singLyric();
    }

    @Override
    public void dance() {
        System.out.println(">> good singer: dance:");
        this.danceHard();
    }

    @Override
    public void singDance() {
        System.out.println(">> good singer: sing:");
        this.singLyric();
        this.danceHard();
    }

    private void singLyric() {
        System.out.println("  Who can says I can't be free\n" +
                "  From all of the things that I used to be");
    }

    private void danceHard() {
        System.out.println("  jump! jump!! jump!!!");
    }
}
