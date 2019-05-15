package jun.prospring5.ch4;

public class CustomEditorExample {

    private FullName fullName;

    public FullName getFullName() {
        return fullName;
    }

    public void setFullName(FullName fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "CustomEditorExample{" +
                "fullName=" + fullName +
                '}';
    }
}
