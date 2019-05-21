package jun.prospring5.ch5;

public class GoodSinger implements Singer {

    @Override
    public void sing(String songName) {
        System.out.println(">> good singer <<");
        System.out.println("  name: " + songName);
    }
}
