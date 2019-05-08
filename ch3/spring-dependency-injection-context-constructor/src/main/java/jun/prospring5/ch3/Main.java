package jun.prospring5.ch3;

public class Main {

    public static void main(String[] args) {
        MessageRenderer renderer =
                MessageSupportFactory.
                        getMessageRenderer();
        renderer.render();
    }
}
