package jun.prospring5.ch3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service("messageRenderer")
public class MessageRendererStandardOutput implements MessageRenderer {

    @Autowired
    private MessageProvider provider;

    @Override
    public void setMessageProvider(MessageProvider provider) {
        Objects.requireNonNull(provider);
        this.provider = provider;
    }

    @Override
    public void render() {
        Objects.requireNonNull(this.provider);
        String message = this.provider.getMessage();
        System.out.println(message);
    }
}
