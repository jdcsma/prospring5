package jun.prospring5.ch3;

public class MessageProviderText implements MessageProvider {

    private String message;

    public MessageProviderText(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
