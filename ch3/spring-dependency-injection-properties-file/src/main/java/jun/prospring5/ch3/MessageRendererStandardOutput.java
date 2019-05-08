package jun.prospring5.ch3;

import java.util.Objects;

public class MessageRendererStandardOutput implements MessageRenderer {

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
