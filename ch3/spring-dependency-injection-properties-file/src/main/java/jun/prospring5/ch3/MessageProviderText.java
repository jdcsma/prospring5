package jun.prospring5.ch3;

public class MessageProviderText implements MessageProvider {

    @Override
    public String getMessage() {
        return "Demo dependency injection with properties file.";
    }
}
