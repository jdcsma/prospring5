package jun.prospring5.ch3;

public class StringHolder {

    private int number;
    private String usage;
    private String content;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "StringHolder{" +
                "number=" + number +
                ", usage='" + usage + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
