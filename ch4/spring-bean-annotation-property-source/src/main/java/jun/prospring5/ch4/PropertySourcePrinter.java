package jun.prospring5.ch4;

public class PropertySourcePrinter {

    private String message;

    public PropertySourcePrinter(String message) {
        this.message = message;
    }

    public void print() {
        System.out.println("message: " + this.message);
    }
}
