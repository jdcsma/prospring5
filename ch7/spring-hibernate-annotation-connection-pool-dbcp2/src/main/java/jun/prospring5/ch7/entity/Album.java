package jun.prospring5.ch7.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "album")
public class Album extends AbstructEntity {

    @Column(name = "title")
    private String title;

    @Temporal(TemporalType.DATE)
    @Column(name = "release_date")
    private Date releaseDate;

    @ManyToOne
    private Singer singer;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Singer getSinger() {
        return singer;
    }

    public void setSinger(Singer singer) {
        this.singer = singer;
    }

    @Override
    public String toString() {
        return "Album{" +
                "id=" + getId() +
                ", version=" + getVersion() +
                ", title='" + getTitle() + '\'' +
                ", releaseDate=" + getReleaseDate() +
                ", singer='" + getSinger().getFirstName() +
                    " " + getSinger().getLastName() + "\'" +
                '}';
    }
}
