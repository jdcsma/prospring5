package jun.prospring5.ch3;

public class MessageProviderText implements MessageProvider {

    private String message;

    public MessageProviderText(String message1, String message2) {
        this.message = message1 + message2;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
