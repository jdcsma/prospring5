package jun.prospring5.ch3;

public interface MessageRenderer {
    void setMessageProvider(MessageProvider provider);
    void render();
}
