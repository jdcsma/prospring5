package jun.prospring5.ch3;

public class MessageProviderText implements MessageProvider {

    private String message;

    public MessageProviderText(String message, int number) {
        this.message = Integer.toString(number) + "." + message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
