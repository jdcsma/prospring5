package jun.prospring5.ch4;

public class MessageRenderer {

    private MessageProvider provider;

    public void setProvider(MessageProvider provider) {
        this.provider = provider;
    }

    public void render() {
        System.out.println("message: " + this.provider.getMessage());
    }
}
