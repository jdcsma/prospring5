package jun.prospring5.ch6.dao.factory;

import jun.prospring5.ch6.entity.Album;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class AlbumFactory {

    public static Album newAlbumFromResultSet(
            ResultSet resultSet) throws SQLException {

        Album album = new Album();

        album.setId(resultSet.getLong("id"));
        album.setSingerId(resultSet.getLong("singer_id"));
        album.setTitle(resultSet.getString("title"));
        album.setReleaseDate(resultSet.getDate("release_date"));

        return album;
    }
}
