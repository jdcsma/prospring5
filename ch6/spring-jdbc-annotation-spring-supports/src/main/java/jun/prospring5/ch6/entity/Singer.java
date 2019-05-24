package jun.prospring5.ch6.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


public class Singer implements Serializable {

    private Long id;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private List<Album> albums;
    private boolean isDeath;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public List<Album> getAlbums() {
        if (albums == null) {
            albums = new ArrayList<>();
        }
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    public boolean isDeath() {
        return isDeath;
    }

    public void setDeath(boolean death) {
        isDeath = death;
    }

    public void addAlbum(Album album) {
        if (album != null) {
            album.setSingerId(this.getId());
            getAlbums().add(album);
        }
    }

    @Override
    public String toString() {
        return "Singer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", isDeath=" + isDeath +
                '}';
    }
}
