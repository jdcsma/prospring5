package jun.prospring5.ch6.dao.implementation;

import jun.prospring5.ch6.dao.SingerDao;
import jun.prospring5.ch6.dao.mapper.JdbcAlbumRowMapper;
import jun.prospring5.ch6.dao.mapper.JdbcSingerResultSetExtractor;
import jun.prospring5.ch6.dao.mapper.JdbcSingerRowMapper;
import jun.prospring5.ch6.entity.Album;
import jun.prospring5.ch6.entity.Singer;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

public class JdbcSingerDao implements SingerDao, InitializingBean {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
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
                "SELECT * FROM singer", new JdbcSingerRowMapper());
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
        return jdbcTemplate.query(
                "SELECT * FROM album WHERE singer_id=?",
                new Object[]{singerId}, new JdbcAlbumRowMapper());
    }

    @Override
    public Singer findById(Long id) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM singer WHERE id=?",
                new Object[]{id}, new JdbcSingerRowMapper());
    }

    @Override
    public String findFirstNameById(Long id) {
        return jdbcTemplate.queryForObject(
                "SELECT first_name FROM singer WHERE id=?",
                new Object[]{id}, String.class);
    }

    @Override
    public String findLastNameById(Long id) {
        return jdbcTemplate.queryForObject(
                "SELECT last_name FROM singer WHERE id=?",
                new Object[]{id}, String.class);
    }

    @Override
    public void insert(Singer singer) {
        this.insertSinger(singer);
        this.insertAlbums(singer);
    }

    @Override
    public void update(Singer singer) {
        jdbcTemplate.update("UPDATE singer " +
                        "SET first_name=?,last_name=?,birth_date=?,is_death=? WHERE id=?",
                singer.getFirstName(), singer.getLastName(),
                singer.getBirthDate(), singer.isDeath(), singer.getId());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM album WHERE singer_id=?", id);
        jdbcTemplate.update("DELETE FROM singer WHERE id=?", id);
    }

    private void insertSinger(Singer singer) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO singer (first_name," +
                            "last_name,birth_date,is_death) VALUES (?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, singer.getFirstName());
            statement.setString(2, singer.getLastName());
            statement.setDate(3, singer.getBirthDate());
            statement.setBoolean(4, singer.isDeath());
            return statement;
        }, keyHolder);

        singer.setId(keyHolder.getKey().longValue());
    }

    private void insertAlbums(Singer singer) {

        if (singer.getAlbums().size() == 0) {
            return;
        }

        for (Album album : singer.getAlbums()) {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            album.setSingerId(singer.getId());
            jdbcTemplate.update(connection -> {
                PreparedStatement statement =
                        connection.prepareStatement(
                                "INSERT INTO album (singer_id, title, release_date) " +
                                        "VALUES (?,?,?)",
                                Statement.RETURN_GENERATED_KEYS);
                statement.setLong(1, album.getSingerId());
                statement.setString(2, album.getTitle());
                statement.setDate(3, album.getReleaseDate());
                return statement;
            }, keyHolder);

            album.setId(keyHolder.getKey().longValue());
        }
    }
}
