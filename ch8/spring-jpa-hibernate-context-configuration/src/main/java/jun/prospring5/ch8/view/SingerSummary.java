package jun.prospring5.ch8.view;

import java.io.Serializable;

public class SingerSummary implements Serializable {

    private String firstName;
    private String lastName;
    private String latestalbum;

    public SingerSummary(
            String firstName, String lastName,
            String latestalbum) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.latestalbum = latestalbum;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLatestalbum() {
        return latestalbum;
    }

    public void setLatestalbum(String latestalbum) {
        this.latestalbum = latestalbum;
    }

    @Override
    public String toString() {
        return "SingerSummary{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", latestalbum='" + latestalbum + '\'' +
                '}';
    }
}
