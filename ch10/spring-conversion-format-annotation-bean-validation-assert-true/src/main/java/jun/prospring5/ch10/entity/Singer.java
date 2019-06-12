package jun.prospring5.ch10.entity;

import org.joda.time.DateTime;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.AssertTrue;

public class Singer {

    private String firstName;
    private String lastName;
    private DateTime birthDate;
    private Genre genre;
    private Gender gender;

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

    public DateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(DateTime birthDate) {
        this.birthDate = birthDate;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @AssertTrue(message = "ERROR! Individual customer should has gender defined")
    public boolean isCountrySinger() {

        if (getGenre() == null) {
            return false;
        }

        if (getGenre() != Genre.COUNTRY) {
            return false;
        }

        return true;
    }

    @AssertFalse(message = "Demonstrate the @AssertFalse annotation.")
    public boolean isInvalidGender() {
        return getGender() == null;
    }

    @Override
    public String toString() {
        return "Singer{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", genre=" + genre +
                ", gender=" + gender +
                '}';
    }
}
