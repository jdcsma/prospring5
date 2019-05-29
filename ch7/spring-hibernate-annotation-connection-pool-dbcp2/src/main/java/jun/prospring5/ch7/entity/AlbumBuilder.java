package jun.prospring5.ch7.entity;

import java.util.Date;
import java.util.Objects;

public final class AlbumBuilder {

    private String title;
    private Date releaseDate;

    public AlbumBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public AlbumBuilder setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    public Album build() {
        Album album = new Album();
        album.setTitle(Objects.requireNonNull(title));
        album.setReleaseDate(Objects.requireNonNull(releaseDate));
        return album;
    }
}
