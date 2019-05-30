package jun.prospring5.ch8.entity;

import java.util.Date;
import java.util.Objects;

public final class SingerBuilder {

    private String firstName;
    private String lastName;
    private Date birthDate;

    public SingerBuilder setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public SingerBuilder setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public SingerBuilder setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
        return this;
    }


    public Singer build() {
        Singer singer = new Singer();
        singer.setFirstName(Objects.requireNonNull(firstName));
        singer.setLastName(Objects.requireNonNull(lastName));
        singer.setBirthDate(Objects.requireNonNull(birthDate));
        return singer;
    }
}
