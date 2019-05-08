package jun.prospring5.ch3;

import org.springframework.stereotype.Component;

@Component("messageProvider")
public class MessageProviderText implements MessageProvider {

    @Override
    public String getMessage() {
        return "Demo dependency injection with context file and annotation.";
    }
}
