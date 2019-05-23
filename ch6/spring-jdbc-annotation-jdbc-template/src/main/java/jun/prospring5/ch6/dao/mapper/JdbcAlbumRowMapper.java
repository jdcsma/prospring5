package jun.prospring5.ch6.dao.mapper;

import jun.prospring5.ch6.entity.Album;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcAlbumRowMapper implements RowMapper<Album> {

    @Override
    public Album mapRow(ResultSet rs, int rowNum)
            throws SQLException {
        Album album = new Album();
        album.setId(rs.getLong("id"));
        album.setSingerId(rs.getLong("singer_id"));
        album.setTitle(rs.getString("title"));
        album.setReleaseDate(rs.getDate("release_date"));
        return album;
    }
}
