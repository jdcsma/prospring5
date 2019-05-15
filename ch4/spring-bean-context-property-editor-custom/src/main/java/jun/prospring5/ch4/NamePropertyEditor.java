package jun.prospring5.ch4;

import java.beans.PropertyEditorSupport;

public class NamePropertyEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        final String[] name = text.split(" ");
        this.setValue(new FullName(name[0], name[1]));
    }
}
