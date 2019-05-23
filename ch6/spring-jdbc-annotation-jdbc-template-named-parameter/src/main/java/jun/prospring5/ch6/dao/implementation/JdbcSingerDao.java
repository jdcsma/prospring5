package jun.prospring5.ch6.dao.implementation;

import jun.prospring5.ch6.dao.SingerDao;
import jun.prospring5.ch6.dao.mapper.JdbcAlbumRowMapper;
import jun.prospring5.ch6.dao.mapper.JdbcSingerResultSetExtractor;
import jun.prospring5.ch6.dao.mapper.JdbcSingerRowMapper;
import jun.prospring5.ch6.entity.Album;
import jun.prospring5.ch6.entity.Singer;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.util.List;

public class JdbcSingerDao implements SingerDao, InitializingBean {

    private NamedParameterJdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (jdbcTemplate == null) {
            throw new BeanCreationException("Null JdbcTemplate on JdbcSingerDao");
        }
    }

    @Override
    public List<Singer> findAll() {

        return jdbcTemplate.query(
                "SELECT * FROM singer",
                new JdbcSingerRowMapper());
    }

    @Override
    public List<Singer> findAllWithDetail() {
        return jdbcTemplate.query(
                "SELECT s.id,s.first_name,s.last_name,s.birth_date,s.is_death, " +
                        "a.id as album_id,a.title,a.release_date FROM singer s " +
                        "LEFT JOIN album a ON s.id = a.singer_id",
                new JdbcSingerResultSetExtractor());
    }

    @Override
    public List<Album> findAlbums(Long singerId) {
        SqlParameterSource parameters =
                new MapSqlParameterSource()
                        .addValue("singerId", singerId);
        return jdbcTemplate.query(
                "SELECT * FROM album WHERE singer_id=:singerId",
                parameters, new JdbcAlbumRowMapper());
    }

    @Override
    public Singer findById(Long id) {
        SqlParameterSource parameters =
                new MapSqlParameterSource()
                        .addValue("id", id);
        return jdbcTemplate.queryForObject(
                "SELECT * FROM singer WHERE id=:id",
                parameters, new JdbcSingerRowMapper());
    }

    @Override
    public String findFirstNameById(Long id) {
        SqlParameterSource parameters =
                new MapSqlParameterSource()
                        .addValue("id", id);
        return jdbcTemplate.queryForObject(
                "SELECT first_name FROM singer WHERE id=:id",
                parameters, String.class);
    }

    @Override
    public String findLastNameById(Long id) {
        SqlParameterSource parameters =
                new MapSqlParameterSource()
                        .addValue("id", id);
        return jdbcTemplate.queryForObject(
                "SELECT last_name FROM singer WHERE id=:id",
                parameters, String.class);
    }

    @Override
    public void insert(Singer singer) {
        this.insertSinger(singer);
        this.insertAlbums(singer);
    }

    @Override
    public void update(Singer singer) {
        SqlParameterSource parameters =
                new MapSqlParameterSource()
                        .addValue("first_name", singer.getFirstName())
                        .addValue("last_name", singer.getLastName())
                        .addValue("birth_date", singer.getBirthDate())
                        .addValue("is_death", singer.isDeath())
                        .addValue("id", singer.getId());

        jdbcTemplate.update("UPDATE singer " +
                "SET first_name=:first_name,last_name=:last_name,birth_date=:birth_date " +
                "WHERE id=:id", parameters);
    }

    @Override
    public void delete(Long id) {
        SqlParameterSource parameters =
                new MapSqlParameterSource()
                        .addValue("id", id);
        jdbcTemplate.update(
                "DELETE FROM album WHERE singer_id=:id", parameters);
        jdbcTemplate.update(
                "DELETE FROM singer WHERE id=:id", parameters);
    }

    private void insertSinger(Singer singer) {

        SqlParameterSource parameters =
                new MapSqlParameterSource()
                        .addValue("first_name", singer.getFirstName())
                        .addValue("last_name", singer.getLastName())
                        .addValue("birth_date", singer.getBirthDate())
                        .addValue("is_death", singer.isDeath());
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update("INSERT INTO singer (" +
                        "first_name,last_name,birth_date,is_death) " +
                        "VALUES (:first_name,:last_name,:birth_date,:is_death)",
                parameters, keyHolder);

        singer.setId(keyHolder.getKey().longValue());
    }

    private void insertAlbums(Singer singer) {

        if (singer.getAlbums().size() == 0) {
            return;
        }

        if (singer.getId() == 0) {
            throw new IllegalArgumentException("singer's id must != 0");
        }

        for (Album album : singer.getAlbums()) {
            album.setSingerId(singer.getId());
            MapSqlParameterSource parameters =
                    new MapSqlParameterSource()
                            .addValue("singer_id", album.getSingerId())
                            .addValue("title", album.getTitle())
                            .addValue("release_date", album.getReleaseDate());
            KeyHolder keyHolder = new GeneratedKeyHolder();

            jdbcTemplate.update("INSERT INTO album " +
                            "(singer_id, title, release_date) " +
                            "VALUES (:singer_id,:title,:release_date)",
                    parameters, keyHolder);

            album.setId(keyHolder.getKey().longValue());
        }
    }
}
