package jun.prospring5.ch4;

public class PropertySourcePrinter {

    private String message;

    public void setMessage(String message) {
        this.message = message;
    }

    public void print() {
        System.out.println("message: " + this.message);
    }
}
